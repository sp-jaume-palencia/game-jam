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
		this.ownerId = MathUtils.random(1, 4);
		this.numTroops = MathUtils.random(0, 99);
	}
}
