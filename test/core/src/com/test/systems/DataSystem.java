package com.test.systems;

import com.badlogic.gdx.math.Vector2;
import com.test.data.BaseData;
import com.test.data.GameState;
import com.test.data.MapData;
import com.test.data.MapState;

public class DataSystem
{
	public MapData map;
	public MapState mapState;
	public GameState gameState;
	
	public void load()
	{
		map = new MapData();
		mapState = new MapState(map);
		
		gameState = new GameState();
	}
}
