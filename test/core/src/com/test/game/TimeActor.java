package com.test.game;

import java.util.Map;

import com.test.utils.StepVariations;
import com.test.utils.Update;

public class TimeActor {
	public StepVariations isAlive;
	
	public void update(Map<String,Update> map) {
		isAlive.update(map.get("isAlive"));
	}
}
