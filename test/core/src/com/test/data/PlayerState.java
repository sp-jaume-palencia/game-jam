package com.test.data;

import com.badlogic.gdx.graphics.Color;

public class PlayerState
{
	public int id;
	public int totalTroops;
	public int points;
	
	public PlayerState()
	{
	}
	
	public PlayerState(int id, int totalTroops, int points)
	{
		this.id = id;
		this.totalTroops = totalTroops;
		this.points = points;
	}
	
	static public Color getPlayerColor(int playerId)
	{
		switch(playerId)
		{
			case 1: return new Color(0/255f, 76/255f, 152/255f, 1f);
			case 2: return new Color(224/255f, 1/255f, 9/255f, 1f);
			case 3: return new Color(0/255f, 107/255f, 51/255f, 1f);
			case 4: return new Color(254/255f, 238/255f, 0/255f, 1f);
		}
		
		return null;
	}
}
