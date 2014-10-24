package com.test.game;

import java.util.Map;

import com.test.systems.RootSystem;
import com.test.utils.InterpolatedVariations;
import com.test.utils.StepVariations;
import com.test.utils.TimeVariations;
import com.test.utils.Update;

public class TimeSquad {
	StepVariations isAlive;
	StepVariations upgrade;
	InterpolatedVariations attack;
	InterpolatedVariations life;
	StepVariations base;
	InterpolatedVariations posX;
	InterpolatedVariations posY;

	public TimeSquad() {};
	
	public void update(Map<String,Update> map) {
		isAlive.update(map.get("isAlive"));
		upgrade.update(map.get("upgrade"));
		attack.update(map.get("attack"));
		life.update(map.get("life"));
		base.update(map.get("base"));
		posX.update(map.get("posX"));
		posY.update(map.get("posY"));
	}
}
