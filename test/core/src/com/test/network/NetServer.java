package com.test.network;

import java.io.IOException;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.test.model.net.CommandAction;
import com.test.network.Network;
import com.test.network.Network.GameActionID;
import com.test.network.Network.GameAddChangeStat;
import com.test.network.Network.GameCommand;
import com.test.network.Network.GameDelChangeStat;
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
					switch(a[rc.actionID])
					{
						case GETROOMS:
							sendRooms(connection.getID());
							break;
						case JOINROOM:
							joinRoom(connection.getID(), rc.roomID);
							break;
						case QUITROOM:
							quitRoom(connection.getID(), rc.roomID);
							break;
						default:
							break;
					}
					return;
				}

				if (object instanceof GameCommand)
				{
					GameCommand rc =((GameCommand)object);
					addCommand(rc);
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
	
	protected void addCommand(GameCommand rc)
	{
		GameActionID[] a = GameActionID.values();
		switch(a[rc.actionID])
		{
			case BASEATACKBASE:
				CommandAction ca = new CommandAction(GameActionID.BASEATACKBASE, rc.objectID, rc.value1);
				RootSystem.commands.addCommand(ca);
				break;
			default:
				break;
		}
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
			rc.roomID = roomId;
			rc.actionID = Network.RoomActionID.STARTGAME.getValue();
			
			server.sendToAllTCP(rc);
		}
	}

	protected void sendRooms(int connId)
	{
		RoomInfo[] rooms = worldServer.getRooms();
		server.sendToTCP(connId, rooms);
	}
	
	public void sendAddChangeStat(int gametime, int objectID, int statID, int value)
	{
		GameAddChangeStat gcs = new GameAddChangeStat();
		gcs.gametime = gametime;
		gcs.objectID = objectID;
		gcs.statID = statID;
		gcs.value = value;
		server.sendToAllTCP(gcs);
	}

	public void sendDelChangeStat(int gametime, int objectID, int statID)
	{
		GameDelChangeStat gcs = new GameDelChangeStat();
		gcs.gametime = gametime;
		gcs.objectID = objectID;
		gcs.statID = statID;
		server.sendToAllTCP(gcs);
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
}