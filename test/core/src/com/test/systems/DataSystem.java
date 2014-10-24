package com.test.systems;

import com.test.data.BaseData;
import com.test.data.MapData;

public class DataSystem
{
	public MapData map;

	public void load()
	{
		map = new MapData();
		map.addBase(1, new BaseData(1));
		map.addBase(2, new BaseData(2));
		map.addBase(3, new BaseData(3));
		map.addBase(4, new BaseData(4));
		map.addBase(5, new BaseData(5));
		
	}

}
