package com.test.systems;

import com.badlogic.gdx.math.Vector2;
import com.test.data.BaseData;
import com.test.data.MapData;
import com.test.data.PlayerState;
import com.test.game.MapState;

public class DataSystem
{
	public PlayerState playerState;
	public MapData map;
	public MapState mapState;
	public long initTimestamp;
	
	public void load()
	{
		map = new MapData();
		mapState = new MapState();
		playerState = new PlayerState();
	}
}
