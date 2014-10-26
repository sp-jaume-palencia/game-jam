package com.test.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.test.data.BaseData;
import com.test.data.BaseState;
import com.test.model.net.CommandAction;
import com.test.network.Network;
import com.test.network.Network.GameActionID;
import com.test.network.Network.GameAttack;
import com.test.network.Network.GameEndOfTurn;
import com.test.network.Network.GameFinish;
import com.test.network.Network.GameStateOfGame;
import com.test.network.Network.GameBaseState;
import com.test.network.Network.GameYourTurn;
import com.test.network.Network.RoomActionID;
import com.test.network.Network.RoomCommand;
import com.test.network.Network.RoomInfo;
import com.test.network.model.NetRoom;
import com.test.network.model.NetWorld;
import com.test.systems.RootSystem;
import com.esotericsoftware.minlog.Log;

public class NetServer
{
	Server server;
	public NetWorld worldServer;

	public NetServer () throws IOException
	{
		server = new Server()
		{
			protected Connection newConnection ()
			{
				// By providing our own connection implementation, we can store per
				// connection state without a connection ID to state look up.
				return new GameConnection();
			}
		};
		
		worldServer = new NetWorld();

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(server);

		server.addListener(new Listener() 
		{
			public void received (Connection c, Object object)
			{
				GameConnection connection = (GameConnection)c;

				if (object instanceof RoomCommand)
				{
					RoomCommand rc =((RoomCommand)object);
					RoomActionID[] a = RoomActionID.values();
					switch(a[rc.actionId])
					{
						case GETROOMS:
							sendRooms(connection.getID());
							break;
						case JOINROOM:
							joinRoom(connection.getID(), rc.roomId);
							break;
						case QUITROOM:
							quitRoom(connection.getID(), rc.roomId);
							break;
						default:
							break;
					}
					return;
				}

				if (object instanceof GameAttack)
				{
					GameAttack rc =((GameAttack)object);
					addAttack(rc);
					server.sendToAllExceptTCP(connection.getID(), rc);
					return;
				}
			}

			public void disconnected (Connection c)
			{
				GameConnection connection = (GameConnection)c;
				if (connection.name != null)
				{
					// Announce to everyone that someone (with a registered name) has left.
				}
			}
		});
		server.bind(Network.port);
		server.start();
	}
	
	protected void addAttack(GameAttack rc)
	{
		Log.info("ATTACK player: "+rc.player+" turn: "+rc.gameTurn+" origin: "+rc.originId+" target: "+rc.targetId);
		RootSystem.commands.addAttack(rc);
		
		server.sendToTCP(worldServer.rooms[rc.roomId].players[0], rc);
		server.sendToTCP(worldServer.rooms[rc.roomId].players[1], rc);
		server.sendToTCP(worldServer.rooms[rc.roomId].players[2], rc);
		server.sendToTCP(worldServer.rooms[rc.roomId].players[3], rc);
	}

	protected void quitRoom(int id, int roomID)
	{
		//TODO
	}

	protected void joinRoom(int connId, int roomId)
	{
		Log.info("JOINROOM room"+roomId+"++");
		
		worldServer.playerJoinRoom(roomId, connId);
		if(worldServer.roomIsReady(roomId))
		{
			worldServer.startGame(roomId);
			RoomCommand rc1 = new RoomCommand();
			RoomCommand rc2 = new RoomCommand();
			RoomCommand rc3 = new RoomCommand();
			RoomCommand rc4 = new RoomCommand();
			rc1.roomId = roomId;
			rc1.actionId = Network.RoomActionID.STARTGAME.getValue();
			rc2.roomId = roomId;
			rc2.actionId = Network.RoomActionID.STARTGAME.getValue();
			rc3.roomId = roomId;
			rc3.actionId = Network.RoomActionID.STARTGAME.getValue();
			rc4.roomId = roomId;
			rc4.actionId = Network.RoomActionID.STARTGAME.getValue();
			
			rc1.player = 1;
			server.sendToTCP(worldServer.rooms[roomId].players[0], rc1);
			rc2.player = 2;
			server.sendToTCP(worldServer.rooms[roomId].players[1], rc2);
			rc3.player = 3;
			server.sendToTCP(worldServer.rooms[roomId].players[2], rc3);
			rc4.player = 4;
			server.sendToTCP(worldServer.rooms[roomId].players[3], rc4);
			
			sendNewState(roomId);

		}
	}

	protected void sendRooms(int connId)
	{
		RoomInfo[] rooms = worldServer.getRooms();
		server.sendToTCP(connId, rooms);
	}
	
	public void sendNewState(int roomId)
	{
		GameStateOfGame sog = new GameStateOfGame();
		sog.bases = new GameBaseState[RootSystem.constants.numBases]; 
				
		int i=0;
		
		for(Entry<Integer, BaseState> base : RootSystem.net.server.worldServer.rooms[roomId].mapState.baseStates.entrySet())
		{
			sog.bases[i] = new GameBaseState();
			sog.bases[i].baseId = base.getValue().baseId;
			sog.bases[i].numTroops = base.getValue().numTroops; 
			sog.bases[i].ownerId = base.getValue().ownerId;
			
			i++;
		}

		server.sendToTCP(worldServer.rooms[roomId].players[0], sog);
		server.sendToTCP(worldServer.rooms[roomId].players[1], sog);
		server.sendToTCP(worldServer.rooms[roomId].players[2], sog);
		server.sendToTCP(worldServer.rooms[roomId].players[3], sog);

	}
	
	private void updateBasePlayer(int roomId, int newPlayer)
	{
		for(Entry<Integer, BaseState> base : RootSystem.net.server.worldServer.rooms[roomId].mapState.baseStates.entrySet())
		{
			if(base.getValue().ownerId == (newPlayer%4)+1)
			{
				int rnd = (int) (Math.random() * 10); 
				if(rnd < 2)
				{
					base.getValue().numTroops+=1;	
				}
				else if(rnd < 8)
				{
					base.getValue().numTroops+=2;
				}
				else
				{
					base.getValue().numTroops+=3;
				}
			}
		}
	}
	
	void stopServer()
	{
		server.stop();
	}

	// This holds per connection state.
	static class GameConnection extends Connection
	{
		public String name;
	}

	public static void main (String[] args) throws IOException {
		Log.set(Log.LEVEL_DEBUG);
		new NetServer();
	}

	public void update(float delta)
	{
		for(NetRoom room : worldServer.rooms)
		{
			room.update(delta);
		}
	}

	public void sendEndTurn(int roomId, int newTurn, int newPlayer)
	{
		Log.info("ENDTURN newTurn: "+newTurn+" newPlayer: "+newPlayer);
		
		GameEndOfTurn turn = new GameEndOfTurn();
		turn.turn = newTurn;
		turn.player = newPlayer;
		server.sendToTCP(worldServer.rooms[roomId].players[0], turn);
		server.sendToTCP(worldServer.rooms[roomId].players[1], turn);
		server.sendToTCP(worldServer.rooms[roomId].players[2], turn);
		server.sendToTCP(worldServer.rooms[roomId].players[3], turn);
		
		RootSystem.commands.processAttacks(roomId);
		
		updateBasePlayer(roomId, newPlayer);
		
		sendNewState(roomId);
	}

	public void sendNewPlayer(int roomId, int newTurn, int newPlayer)
	{
		Log.info("NEWPLAYER newTurn: "+newTurn+" newPlayer: "+newPlayer);
		
		GameYourTurn turn = new GameYourTurn();
		turn.turn = newTurn;
		turn.player = newPlayer;
		server.sendToTCP(worldServer.rooms[roomId].players[0], turn);
		server.sendToTCP(worldServer.rooms[roomId].players[1], turn);
		server.sendToTCP(worldServer.rooms[roomId].players[2], turn);
		server.sendToTCP(worldServer.rooms[roomId].players[3], turn);
	}

	public void sendFinishGame(int roomId)
	{
		Log.info("FINIH GAME winner: 000");
		
		GameFinish end = new GameFinish();
		end.player = 1;
		server.sendToTCP(worldServer.rooms[roomId].players[0], end);
		server.sendToTCP(worldServer.rooms[roomId].players[1], end);
		server.sendToTCP(worldServer.rooms[roomId].players[2], end);
		server.sendToTCP(worldServer.rooms[roomId].players[3], end);
		
	}

	
}