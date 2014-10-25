package com.test.systems;

public class ConstantsSystem {
	public int beginTime;
	public int endTime;
	
	public int alive;
	public int dead;
	
	public int turnTime;
	public int gameTime;
	
	public void load()
	{
		//Time
		beginTime = 0;
		endTime = 300000;
		
		//GameObjects
		alive = 1;
		dead = 0;
		
		turnTime = 5000;
		gameTime = 300000;
		
	}
}
