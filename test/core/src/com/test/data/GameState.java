package com.test.data;

import com.test.network.Network.GameEndOfTurn;
import com.test.network.Network.GameYourTurn;

public class GameState
{

	public long initTimestamp;
	public long currentTurn;
	public long currentPlayer;

	public void startGame(long millis)
	{
		initTimestamp = millis;
		currentTurn = 0;
		currentPlayer = 0;
	}

	public void endOfTurn(GameEndOfTurn endOfTurn)
	{
	//Finish turn
		
	}

	public void turnBegin(GameYourTurn yourTurn)
	{
		currentTurn = yourTurn.turn;
		currentPlayer = yourTurn.player;		
	}

	public void finishGame(int player)
	{
		//finish game
	}
		
}
