package com.test.game;

import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.test.data.BaseData;
import com.test.model.time.InterpolatedVariations;
import com.test.model.time.StepVariations;
import com.test.utils.Update;

public class TimeBase {
	public int baseId;
	public int[] annexedBases;
	public Vector2 position;
	public StepVariations owner;
	public StepVariations attack;
	public StepVariations target;
	public InterpolatedVariations life;
	public StepVariations upgrade;
	public InterpolatedVariations resourceProduction;
	public InterpolatedVariations unitsProduction;
	public BaseData updatedData;
	
	public TimeBase(int baseId, Vector2 position, int[] annexedBases) {
		this.baseId = baseId;
		this.position = position;
		this.annexedBases = annexedBases;
		owner = new StepVariations();
		attack = new StepVariations();
		target = new StepVariations();
		life = new InterpolatedVariations();
		upgrade = new StepVariations();
		resourceProduction = new InterpolatedVariations();
		unitsProduction = new InterpolatedVariations();
		updatedData = new BaseData(
				0,
				baseId, 
				position,
				annexedBases, 
				owner.getValue(0), 
				attack.getValue(0), 
				target.getValue(0),
				life.getValue(0), 
				upgrade.getValue(0), 
				resourceProduction.getValue(0),
				unitsProduction.getValue(0)
			);
	};
	
	public void update(Map<String,Update> map) {
		owner.update(map.get("update"));
		attack.update(map.get("attack"));
		target.update(map.get("target"));
		life.update(map.get("life"));
		upgrade.update(map.get("upgrade"));
		resourceProduction.update(map.get("resourceProduction"));
		unitsProduction.update(map.get("unitsProduction"));
	}
	
	public void updateState(int time) {
		updatedData.update(
				time,
				baseId, 
				position,
				annexedBases, 
				owner.getValue(time), 
				attack.getValue(time), 
				target.getValue(time),
				life.getValue(time), 
				upgrade.getValue(time), 
				resourceProduction.getValue(time),
				unitsProduction.getValue(time)
			);
	}
	
	public BaseData getBaseData(int time) {
		updateState(time);
		return updatedData;
	}
}
