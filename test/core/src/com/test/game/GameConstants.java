package com.test.game;

public class GameConstants {
	public int beginTime;
	public int endTime;
	
	public int alive;
	public int dead;
	
	public void load()
	{
		//Time
		beginTime = 0;
		endTime = 300000;
		
		//GameObjects
		alive = 1;
		dead = 0;
	}
}
