package com.test.data;

import com.badlogic.gdx.math.Vector2;

public class BaseState {

	public int baseId;
	public int ownerId;
	public int numTroops;
	public BaseData data;
	
	public BaseState(BaseData data)		
	{
		this.data = data;
		this.baseId = 0;
		this.ownerId = 0;
		this.numTroops = 0;
	}
}
