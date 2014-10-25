package com.test.data;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class BaseData
{
	public int baseId;
	public Vector2 position;
	public int[] annexedBases;
	public int group;
	public Texture texture;
	
	public BaseData( 
		int baseId, 
		Vector2 position,
		int[] annexedBases,
		int group,
		Texture texture
	)
	{
		this.baseId = baseId;
		this.position = position;
		this.annexedBases = annexedBases;
		this.group = group;
		this.texture = texture;
	}
}
