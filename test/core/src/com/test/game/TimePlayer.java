package com.test.game;

import java.util.Map;

import com.test.model.time.InterpolatedVariations;
import com.test.utils.Update;

public class TimePlayer {
	public InterpolatedVariations resource;
	public InterpolatedVariations score;
	
	public TimePlayer() {};
	
	public void update(Map<String,Update> map) {
		resource.update(map.get("resource"));
		score.update(map.get("score"));
	}
	
	public int getResource(int time) {
		return resource.getValue(time);
	}
	
	public int getScore(int time) {
		return score.getValue(time);
	}
}
