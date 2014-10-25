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
	public InterpolatedVariations life;
	public StepVariations upgrade;
	public InterpolatedVariations resourceProduction;
	public InterpolatedVariations unitsProduction;
	
	public TimeBase(int baseId, Vector2 position, int[] annexedBases) {
		this.baseId = baseId;
		this.position = position;
		this.annexedBases = annexedBases;
	};
	
	public void update(Map<String,Update> map) {
		owner.update(map.get("update"));
		attack.update(map.get("attack"));
		life.update(map.get("life"));
		upgrade.update(map.get("upgrade"));
		resourceProduction.update(map.get("resourceProduction"));
		unitsProduction.update(map.get("unitsProduction"));
	}
	
	public BaseData getBaseData(int time) {
		return new BaseData(
			baseId, 
			position,
			annexedBases, 
			owner.getValue(time), 
			attack.getValue(time), 
			life.getValue(time), 
			upgrade.getValue(time), 
			resourceProduction.getValue(time),
			unitsProduction.getValue(time)
		);
	}
}
