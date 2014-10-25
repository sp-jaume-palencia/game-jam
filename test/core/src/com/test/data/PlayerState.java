package com.test.data;

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
}
