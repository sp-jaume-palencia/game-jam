package com.test.network;

import java.awt.EventQueue;
import java.io.IOException;

import com.badlogic.gdx.utils.TimeUtils;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.test.network.Network.GameAttack;
import com.test.network.Network.GameBaseState;
import com.test.network.Network.GameEndOfTurn;
import com.test.network.Network.GameFinish;
import com.test.network.Network.GameStateOfGame;
import com.test.network.Network.GameYourTurn;
import com.test.network.Network.RoomCommand;
import com.test.network.Network.RoomInfo;
import com.test.systems.RootSystem;

public class NetClient {
	//ChatFrame chatFrame;
	Client client;
	String name;

	public NetClient () {
		client = new Client();
		client.start();

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(client);

		client.addListener(new Listener()
		{
			public void connected (Connection connection)
			{
			}

			public void received (Connection connection, Object object)
			{
				if (object instanceof RoomCommand)
				{
					RoomCommand rc = (RoomCommand)object;
					startGame(rc.roomId, rc.player);
					return;
				}

				if (object instanceof RoomInfo[])
				{
					RoomInfo[] rooms = (RoomInfo[])object;
					setRoomInfo(rooms);
					return;
				}
				
				if (object instanceof GameYourTurn)
				{
					GameYourTurn yourTurn = (GameYourTurn)object;
					turnBegin(yourTurn);
					return;
				}
				
				if (object instanceof GameEndOfTurn)
				{
					GameEndOfTurn endOfTurn = (GameEndOfTurn)object;
					endOfTurn(endOfTurn);
					return;
				}
				
				if (object instanceof GameStateOfGame)
				{
					GameStateOfGame state = (GameStateOfGame)object;
					updateState(state);
					return;
				}
				
				if (object instanceof GameAttack)
				{
					GameAttack attack = (GameAttack)object;
					updateAttack(attack);
					return;
				}
				
				if (object instanceof GameFinish)
				{
					GameFinish end = (GameFinish)object;
					finishGame(end);
					return;
				}
			}

			public void disconnected (Connection connection) {
				EventQueue.invokeLater(new Runnable() {
					public void run () {
						// Closing the frame calls the close listener which will stop the client's update thread.
						//chatFrame.dispose();
					}
				});
			}
		});

		final String host = "192.168.0.80";

		// We'll do the connect on a new thread so the ChatFrame can show a progress bar.
		// Connecting to localhost is usually so fast you won't see the progress bar.
		new Thread("Connect") {
			public void run () {
				try {
					client.connect(5000, host, Network.port);
					// Server communication after connection can go here, or in Listener#connected().
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(1);
				} 
			}
		}.start();
	}

	protected void finishGame(GameFinish end)
	{
		RootSystem.data.gameState.finishGame(end.player);
	}

	protected void updateAttack(GameAttack attack)
	{
		RootSystem.data.mapState.addAttack(attack);
	}

	protected void updateState(GameStateOfGame state)
	{
		RootSystem.data.mapState.process(state);
	}

	protected void endOfTurn(GameEndOfTurn endOfTurn)
	{
		
		RootSystem.data.gameState.endOfTurn(endOfTurn);
	}

	protected void turnBegin(GameYourTurn yourTurn)
	{
		RootSystem.data.gameState.turnBegin(yourTurn);
	}

	protected void setRoomInfo(RoomInfo[] rooms)
	{
		RootSystem.screens.lobby.setRoomInfo(rooms);
	}

	public void askRoom()
	{
		RoomCommand rc = new RoomCommand();
		rc.actionId = Network.RoomActionID.GETROOMS.getValue();
		client.sendTCP(rc);
	}
	
	public void joinRoom(int roomId)
	{
		RoomCommand rc = new RoomCommand();
		rc.roomId = roomId;
		rc.actionId = Network.RoomActionID.JOINROOM.getValue();
		client.sendTCP(rc);
	}

	public void quitRoom(int roomId)
	{
		RoomCommand rc = new RoomCommand();
		rc.roomId = roomId;
		rc.actionId = Network.RoomActionID.QUITROOM.getValue();
		client.sendTCP(rc);
	}
	
	protected void startGame(int roomId, long player)
	{		
		RootSystem.data.playerState.id = (int) player;
		//find playerId on roomId
		RootSystem.data.gameState.startGame(TimeUtils.millis());
	}
	
	public void sendAttack(int originId, int targetId)
	{
		GameAttack ga = new GameAttack();
		ga.gameTurn = RootSystem.data.gameState.currentTurn;
		ga.player = RootSystem.data.playerState.id;
		ga.originId = originId;
		ga.targetId = targetId;
		client.sendTCP(ga);
	}
	
	public static void main (String[] args) {
		Log.set(Log.LEVEL_DEBUG);
		new NetClient();
	}
}
