package com.test.model.net;

import java.util.TreeMap;

public class BaseStatHistory
{
	int statType;
	
	int initValue;
	int initTime;
	
	int minValue;
	int maxValue;
	
	TreeMap<Integer, Integer> varHistory;
	
	public BaseStatHistory(int statType, int initValue, int initTime, int minValue, int maxValue)
	{
		this.initTime = initTime;
		this.initValue = initValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		
		varHistory = new TreeMap<Integer,Integer>();
	}

	public void addVar(Integer time, Integer modifier)
	{
		varHistory.put(time, modifier);
	}
	
	
}
