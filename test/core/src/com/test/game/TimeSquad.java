package com.test.game;

import java.util.Map;

import com.test.model.time.InterpolatedVariations;
import com.test.model.time.StepVariations;
import com.test.utils.Update;

public class TimeSquad extends TimeActor {
	public StepVariations upgrade;
	public InterpolatedVariations attack;
	public InterpolatedVariations life;
	public StepVariations base;
	public InterpolatedVariations posX;
	public InterpolatedVariations posY;

	public TimeSquad() {};
	
	public void update(Map<String,Update> map) {
		super.update(map);
		upgrade.update(map.get("upgrade"));
		attack.update(map.get("attack"));
		life.update(map.get("life"));
		base.update(map.get("base"));
		posX.update(map.get("posX"));
		posY.update(map.get("posY"));
	}
}
