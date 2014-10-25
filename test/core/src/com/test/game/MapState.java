package com.test.game;

import java.util.HashMap;

import com.test.data.BaseData;
import com.test.data.PlayerState;
import com.test.systems.RootSystem;

public class MapState {
	
	public HashMap<Integer, BaseState> baseStates;
	
	public MapState() 
	{
		baseStates = new HashMap<Integer, BaseState>();
	}
	
	public void addBaseState(int baseId, BaseData baseData) 
	{
		//TODO JAUME
	}
		
	public BaseState getBaseState(int id) 
	{
		return baseStates.get(id);
	}

	public boolean isOwnPlanet(int id)
	{
		BaseState planet = baseStates.get(id);
		PlayerState playerState = RootSystem.data.playerState;
		
		return planet.baseId == playerState.id;
	}
	
	public void attackTo(int planetId)
	{
		
	}
}
