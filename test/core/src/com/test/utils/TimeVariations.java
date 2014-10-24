package com.test.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public abstract class TimeVariations
{
	TreeMap<Integer,Integer> timeMap;
	int begin = 0;
	int end = 300;

	public TimeVariations() {
		timeMap = new TreeMap<Integer,Integer>();
		timeMap.put(begin,0);
		timeMap.put(end, 0);
	}
	
	public void update(ArrayList<Map.Entry<Integer,Integer>> removed, ArrayList<Map.Entry<Integer,Integer>> added) {
		for (Map.Entry<Integer,Integer> r : removed) {
			if (timeMap.containsKey(r.getKey())) {
				timeMap.remove(r.getKey());
			}
		}
		for (Map.Entry<Integer,Integer> a : added) {
			timeMap.put(a.getKey(), a.getValue());
		}
	}
	
	public abstract int getValue(int time);
}

