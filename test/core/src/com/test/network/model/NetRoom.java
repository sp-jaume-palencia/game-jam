package com.test.network.model;

public class NetRoom
{
	int id;
	int players[];
	int numPlayers;

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

}
