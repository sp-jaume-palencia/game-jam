package com.test.network;

import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.test.model.net.CommandAction;
import com.test.network.Network;
import com.test.network.Network.GameActionID;
import com.test.network.Network.GameAttack;
import com.test.network.Network.GameEndOfTurn;
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
	NetWorld worldServer;

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
		RootSystem.commands.addAttack(rc);
	}

	protected void quitRoom(int id, int roomID)
	{
		//TODO
	}

	protected void joinRoom(int connId, int roomId)
	{
		worldServer.playerJoinRoom(roomId, connId);
		if(worldServer.roomIsReady(roomId))
		{
			worldServer.startGame(roomId);
			RoomCommand rc = new RoomCommand();
			rc.roomId = roomId;
			rc.actionId = Network.RoomActionID.STARTGAME.getValue();
			
			server.sendToAllTCP(rc);
		}
	}

	protected void sendRooms(int connId)
	{
		RoomInfo[] rooms = worldServer.getRooms();
		server.sendToTCP(connId, rooms);
	}
	
	public void sendNewState()
	{
		// TODO Auto-generated method stub
		
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

	public void sendEndTurn(int newTurn, int newPlayer)
	{
		GameYourTurn turn = new GameYourTurn();
		turn.turn = newTurn;
		turn.player = newPlayer;
		server.sendToAllTCP(turn);
	}

	public void sendNewPlayer(int newTurn, int newPlayer)
	{
		GameEndOfTurn turn = new GameEndOfTurn();
		turn.turn = newTurn;
		turn.player = newPlayer;
		server.sendToAllTCP(turn);
	}

	
}