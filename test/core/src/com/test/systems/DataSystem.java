package com.test.systems;

import com.test.data.GameState;
import com.test.data.MapData;
import com.test.data.MapState;
import com.test.data.PlayerState;

public class DataSystem
{
	public PlayerState playerState;
	public MapData map;
	public MapState mapState;
	public GameState gameState;
	
	public void load()
	{
		map = new MapData();
		mapState = new MapState(map);
		playerState = new PlayerState();
		gameState = new GameState();
	}
}
