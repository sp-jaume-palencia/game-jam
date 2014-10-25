package com.test.game;

import java.util.Map;

import com.test.model.time.StepVariations;
import com.test.utils.Update;

public abstract class TimeActor {
	public abstract void update(Map<String,Update> map);
}
