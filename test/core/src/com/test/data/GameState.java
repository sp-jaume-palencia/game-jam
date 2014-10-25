package com.test.data;

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
		
}
