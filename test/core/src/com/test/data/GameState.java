package com.test.data;

import com.test.network.Network.GameEndOfTurn;
import com.test.network.Network.GameYourTurn;
import com.test.systems.RootSystem;

public class GameState
{

	public long initTimestamp;
	public int currentTurn;
	public int currentPlayer;

	public void startGame(long millis)
	{
		initTimestamp = millis;
		currentTurn = 0;
		currentPlayer = 0;
		
		RootSystem.screens.lobby.onForcePlay();
	}

	public void endOfTurn(GameEndOfTurn endOfTurn)
	{
		RootSystem.screens.gameplay.endTurn();
	}

	public void turnBegin(GameYourTurn yourTurn)
	{
		currentTurn = yourTurn.turn;
		currentPlayer = yourTurn.player;
		
		RootSystem.screens.gameplay.startTurn(currentPlayer);
	}

	public void finishGame(int player)
	{
		RootSystem.screens.gameplay.gameOver(player);
	}
		
	public boolean isPlayerTurn()
	{
		return currentPlayer == RootSystem.data.playerState.id;
	}
}
