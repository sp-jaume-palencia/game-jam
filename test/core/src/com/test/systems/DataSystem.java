package com.test.systems;

import com.badlogic.gdx.math.Vector2;
import com.test.data.BaseData;
import com.test.data.MapData;
import com.test.game.MapState;

public class DataSystem
{
	public MapData map;
	public MapState mapState;
	
	public void load()
	{
		map = new MapData();
		mapState = new MapState();
	}
}
