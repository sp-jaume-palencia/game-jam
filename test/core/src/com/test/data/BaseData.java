package com.test.data;

import com.badlogic.gdx.math.Vector2;

public class BaseData
{
	public int baseId;
	public Vector2 position;
	public int[] annexedBases;
	
	public BaseData( 
		int baseId, 
		Vector2 position,
		int[] annexedBases)
	{
		this.baseId = baseId;
		this.position = position;
		this.annexedBases = annexedBases;
	}
}
