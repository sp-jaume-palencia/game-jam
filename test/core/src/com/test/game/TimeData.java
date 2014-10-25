package com.test.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.test.utils.Update;

public class TimeData {
	HashMap<Integer, TimeBase> timeData;
	
	public TimeData() {
		timeData = new HashMap<Integer, TimeBase>();
	}
	
	public void update(Map<Integer,Map<String,Update>> map) {
		for (Map.Entry<Integer, Map<String, Update>> entry : map.entrySet()) {
			timeData.get(entry.getKey()).update(entry.getValue());
		}
	}
	
	public TimeBase get(int id) {
		return timeData.get(id);
	}
}
