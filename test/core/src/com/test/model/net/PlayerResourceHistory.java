package com.test.model.net;

import java.util.TreeMap;

public class PlayerResourceHistory
{
	int initValue;
	int initTime;
	
	int minValue;
	int maxValue;
	
	TreeMap<Integer, Integer> varHistory;
	
	public PlayerResourceHistory(int initValue, int initTime, int minValue, int maxValue)
	{
		this.initTime = initTime;
		this.initValue = initValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		
		varHistory = new TreeMap<Integer,Integer>();
	}

	
	
	
}
