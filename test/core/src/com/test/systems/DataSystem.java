package com.test.systems;

import com.badlogic.gdx.math.Vector2;
import com.test.data.BaseData;
import com.test.data.MapData;

public class DataSystem
{
	public MapData map;

	public void load()
	{
		map = new MapData();
		map.addBase(1, new BaseData(1, 1, new Vector2(100, 100)));
		map.addBase(2, new BaseData(2, 1, new Vector2(300, 100)));
		map.addBase(3, new BaseData(3, 1, new Vector2(500, 100)));
		map.addBase(4, new BaseData(4, 1, new Vector2(700, 100)));
		map.addBase(5, new BaseData(5, 2, new Vector2(300, 300)));
		
	}

}
