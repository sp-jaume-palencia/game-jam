package com.test.data;

import com.badlogic.gdx.math.Vector2;

public class BaseData
{
	public int baseId;
	public Vector2 position;
	public int[] annexedBases;
	public int owner;
	public int attack;
	public int life;
	public int upgrade;
	public int resourceProduction;
	public int unitsProduction;
	
	public BaseData(
			int baseId, 
			Vector2 position,
			int[] annexedBases, 
			int owner,
			int attack,
			int life, 
			int upgrade, 
			int resourceProduction,
			int unitsProduction
			) {
			this.baseId = baseId;
			this.position = position;
			this.annexedBases = annexedBases;
			this.owner = owner;
			this.attack = attack;
			this.life = life;
			this.upgrade = upgrade;
			this.resourceProduction = resourceProduction;
			this.unitsProduction = unitsProduction;
	}
}
