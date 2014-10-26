package com.test.network.model;

import com.badlogic.gdx.utils.TimeUtils;
import com.test.data.GameState;
import com.test.data.MapState;
import com.test.systems.RootSystem;

public class NetRoom
{
	int id;
	public int players[];
	int numPlayers;
	long initTimestamp;
	public int currentTurn;
	public int currentPlayer;

	public GameState gameState;
	public MapState mapState;
	

	public NetRoom(int i)
	{
		id = i;
		players = new int[4];
		players[0] = -1;
		players[1] = -1;
		players[2] = -1;
		players[3] = -1;
		numPlayers = 0;
		initTimestamp = 0;
		
		gameState = new GameState();
		mapState = new MapState(RootSystem.data.map);
	}
	
	public boolean addPlayer(int playerId)
	{
		if(numPlayers < players.length)
		{
			for(int i=0;i<4;i++)
			{
				if(players[i] == -1)
				{
					players[i] = playerId;
					numPlayers++;
					return true;
				}
			}
		}
		return false;
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
				RootSystem.net.server.sendEndTurn(id, newTurn, newPlayer+1);
			}
	
			if(newPlayer > currentPlayer || (newPlayer == 0 && currentPlayer !=0))
			{
				currentPlayer = newPlayer;
				RootSystem.net.server.sendNewPlayer(id, newTurn, newPlayer+1);
			}
			
			if(newTurn >= RootSystem.constants.gameTime/RootSystem.constants.turnTime)
			{
				RootSystem.net.server.sendFinishGame(id);
				initTimestamp = 0;
			}
		}
	}
	
	

}
