package com.test.game;

import com.badlogic.gdx.math.Vector2;

public class BaseState {

	public int baseId;
	public int ownerId;
	public int numTroops;
	
	public BaseState( 
		int baseId,
		int ownerId,
		int numTroops)		
	{
		this.baseId = baseId;
		this.ownerId = ownerId;
		this.numTroops = numTroops;
	}
}
