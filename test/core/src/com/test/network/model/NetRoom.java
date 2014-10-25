package com.test.network.model;

import com.badlogic.gdx.utils.TimeUtils;
import com.test.systems.RootSystem;

public class NetRoom
{
	int id;
	int players[];
	int numPlayers;
	long initTimestamp;
	public int currentTurn;
	public int currentPlayer;


	public NetRoom(int i)
	{
		id = i;
		players = new int[4];
		players[0] = 0;
		players[1] = 0;
		players[2] = 0;
		players[3] = 0;
		numPlayers = 0;
		initTimestamp = 0;
	}
	
	public void addPlayer(int playerId)
	{
		if(numPlayers < players.length)
		{
			players[numPlayers] = playerId;
			numPlayers++;
		}
	}

	public void startGame()
	{
		initTimestamp = TimeUtils.millis();
		RootSystem.data.gameState.initTimestamp = initTimestamp;
	}
	
	public void update(float delta)
	{
		if(initTimestamp > 0)
		{
			int newTurn = (int) ((TimeUtils.millis() - initTimestamp)/RootSystem.constants.turnTime);
			int newPlayer = currentTurn%4;
			
			if(newTurn > currentTurn)
			{
				currentTurn = newTurn;
				RootSystem.net.server.sendEndTurn(newTurn, newPlayer);
			}
	
			if(newPlayer > currentPlayer)
			{
				currentPlayer = newPlayer;
				RootSystem.net.server.sendNewPlayer(newTurn, newPlayer);
			}
		}
		
	}

}
