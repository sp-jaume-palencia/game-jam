package com.test.game;

import java.util.Map;

import com.test.model.time.InterpolatedVariations;
import com.test.model.time.StepVariations;
import com.test.utils.Update;

public class TimeBase extends TimeActor {
	public StepVariations owner;
	public StepVariations attack;
	public InterpolatedVariations life;
	public StepVariations upgrade;
	public InterpolatedVariations resourceProduction;
	public InterpolatedVariations unitsProduction;
	public StepVariations isAlive;
	
	public TimeBase() {};
	
	public void update(Map<String,Update> map) {
		isAlive.update(map.get("isAlive"));
		upgrade.update(map.get("upgrade"));
		attack.update(map.get("attack"));
		resourceProduction.update(map.get("resourceProduction"));
		unitsProduction.update(map.get("unitsProduction"));
		life.update(map.get("life"));
	}
	
	public int getOwner(int time) {
		return owner.getValue(time);
	}
	
	public int getIsAlive(int time) {
		return isAlive.getValue(time);
	}
	
	public int getAttack(int time) {
		return attack.getValue(time);
	}
	
	public int getLife(int time) {
		return life.getValue(time);
	}
	
	public int getUpgrade(int time) {
		return upgrade.getValue(time);
	}
	
	public int getResourceProduction(int time) {
		return resourceProduction.getValue(time);
	}
	
	public int getUnitsProduction(int time) {
		return unitsProduction.getValue(time);
	}	
}
