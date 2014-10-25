package com.test.data;

import com.badlogic.gdx.math.Vector2;

public class BaseData
{
	public int baseId;
	public int ownerId;
	public Vector2 position;
	
	public BaseData(int baseId, int playerId, Vector2 pos)
	{
		this.baseId = baseId;
		this.ownerId = playerId;
		this.position = pos;
	}
	
	public BaseData(int baseId)
	{
		this.baseId = baseId;
		ownerId = 0;
	}
	
	public void setOwner(int ownerId)
	{
		this.ownerId = ownerId;
	}

}
