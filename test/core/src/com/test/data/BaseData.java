package com.test.data;

import com.badlogic.gdx.math.Vector2;

public class BaseData
{
	public int time;
	public int baseId;
	public Vector2 position;
	public int[] annexedBases;
	public int owner;
	public int attack;
	public int target;
	public int life;
	public int upgrade;
	public int resourceProduction;
	public int unitsProduction;
	
	public BaseData(
			int time, 
			int baseId, 
			Vector2 position,
			int[] annexedBases, 
			int owner,
			int attack,
			int target,
			int life, 
			int upgrade, 
			int resourceProduction,
			int unitsProduction
			) {
			this.time = time;
			this.baseId = baseId;
			this.position = position;
			this.annexedBases = annexedBases;
			this.owner = owner;
			this.attack = attack;
			this.target = target;
			this.life = life;
			this.upgrade = upgrade;
			this.resourceProduction = resourceProduction;
			this.unitsProduction = unitsProduction;
	}
	
	public void update(
			int time, 
			int baseId, 
			Vector2 position,
			int[] annexedBases, 
			int owner,
			int attack,
			int target,
			int life, 
			int upgrade, 
			int resourceProduction,
			int unitsProduction
			) {
			this.time = time;
			this.baseId = baseId;
			this.position = position;
			this.annexedBases = annexedBases;
			this.owner = owner;
			this.attack = attack;
			this.target = target;
			this.life = life;
			this.upgrade = upgrade;
			this.resourceProduction = resourceProduction;
			this.unitsProduction = unitsProduction;
	}
}
