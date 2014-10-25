package com.test.systems;

public class ConstantsSystem {
	public int beginTime;
	public int endTime;
	
	public int alive;
	public int dead;
	
	public int serverTic;
	
	public void load()
	{
		//Time
		beginTime = 0;
		endTime = 300000;
		
		//GameObjects
		alive = 1;
		dead = 0;
		
		serverTic = 100;
		
		
	}
}
