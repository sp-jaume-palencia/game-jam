package com.test.data;

import com.badlogic.gdx.math.MathUtils;

public class BaseState {

	public int baseId;
	public int ownerId;
	public int numTroops;
	public BaseData data;
	
	public BaseState(BaseData data)		
	{
		this.data = data;
		this.baseId = data.baseId;
		this.ownerId = 0;
		this.numTroops = 0;
	}
}
