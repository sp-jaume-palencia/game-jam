package com.test.game;

import java.util.Map;

import com.test.systems.RootSystem;
import com.test.utils.InterpolatedVariations;
import com.test.utils.StepVariations;
import com.test.utils.TimeVariations;
import com.test.utils.Update;

public class TimeBase {
	StepVariations isAlive;
	StepVariations upgrade;
	InterpolatedVariations resourceProduction;
	InterpolatedVariations unitsProduction;
	InterpolatedVariations life;

	public TimeBase() {};
	
	public void update(Map<String,Update> map) {
		isAlive.update(map.get("isAlive"));
		upgrade.update(map.get("upgrade"));
		resourceProduction.update(map.get("resourceProduction"));
		unitsProduction.update(map.get("unitsProduction"));
		life.update(map.get("life"));
	}
}