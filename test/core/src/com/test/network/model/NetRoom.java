package com.test.network.model;

import com.badlogic.gdx.utils.TimeUtils;
import com.test.systems.RootSystem;

public class NetRoom
{
	int id;
	int players[];
	int numPlayers;
	long initTimestamp;
	long tics;

	public NetRoom(int i)
	{
		id = i;
		players = new int[4];
		players[0] = 0;
		players[1] = 0;
		players[2] = 0;
		players[3] = 0;
		numPlayers = 0;
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
		RootSystem.data.initTimestamp = initTimestamp;
	}
	
	public long updateTics()
	{
		tics = (TimeUtils.millis() - initTimestamp)/RootSystem.constants.serverTic;
		return tics;
	}

}
