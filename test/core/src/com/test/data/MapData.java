package com.test.data;

import java.util.HashMap;

public class MapData
{
	public HashMap<Integer, BaseData> bases;
	
	public MapData()
	{
		bases = new HashMap<Integer, BaseData>();
	}
	
	public void addBase(Integer baseId, BaseData base)
	{
		bases.put(baseId, base);
	}
	
	public BaseData getBase(Integer baseId)
	{
		return bases.get(baseId);
	}
}
