package com.test.game;

import java.util.Map;

import com.test.model.time.StepVariations;
import com.test.utils.Update;

public class TimeActor {
	StepVariations isAlive;
	
	public void update(Map<String,Update> map) {
		isAlive.update(map.get("isAlive"));
	}
}
