package com.test.game;

import java.util.HashMap;

import com.test.data.BaseData;

public class MapState {
	
	public HashMap<Integer, BaseState> baseStates;
	
	public MapState() 
	{
		baseStates = new HashMap<Integer, BaseData>();
	}
	
	public void addBaseState(int baseId, BaseData baseData) 
	{
		baseStates.put(baseId, baseData);
	}
		
	public BaseData getBaseState(int id) 
	{
		return baseStates.get(id);
	}

}
