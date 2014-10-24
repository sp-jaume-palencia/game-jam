package com.test.utils;

import java.util.Map;
import java.util.TreeMap;

import com.test.systems.RootSystem;

public abstract class TimeVariations
{
	TreeMap<Integer,Integer> timeMap;

	public TimeVariations() {
		timeMap = new TreeMap<Integer,Integer>();
		timeMap.put(RootSystem.constants.beginTime,0);
		timeMap.put(RootSystem.constants.endTime, 0);
	}
	
	/*
	public void put(int time, int value) {
		timeMap.put(time, value);
	}
	
	public void remove(int time) {
		timeMap.remove(time);
	}
	*/
	
	public void update(Update update) {
		for (Map.Entry<Integer,Integer> r : update.removed) {
			if (timeMap.containsKey(r.getKey())) {
				timeMap.remove(r.getKey());
			}
			else assert(false);
		}
		for (Map.Entry<Integer,Integer> a : update.added) {
			if (timeMap.containsKey(a.getKey())) assert(false);
			timeMap.put(a.getKey(), a.getValue());
		}
	}
	
	public abstract int getValue(int time);
}
