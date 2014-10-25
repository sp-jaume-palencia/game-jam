package com.test.data;

import com.badlogic.gdx.graphics.Color;

public class PlayerState
{
	public int id;
	public int totalTroops;
	public int points;
	
	public PlayerState()
	{
		id = 1;
	}
	
	public PlayerState(int id, int totalTroops, int points)
	{
		this.id = id;
		this.totalTroops = totalTroops;
		this.points = points;
	}
	
	public Color getPlayerColor(int playerId)
	{
		switch(playerId)
		{
			case 1: return Color.BLUE;
			case 2: return Color.RED;
			case 3: return Color.GREEN;
			case 4: return Color.YELLOW;
		}
		
		return null;
	}
}
