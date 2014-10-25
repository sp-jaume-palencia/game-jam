package com.test.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.math.Vector2;
import com.test.data.BaseData;
import com.test.utils.Update;

public class MapData 
{
	public HashMap<Integer, BaseData> baseDatas;
	
	public MapData() 
	{
		baseDatas = new HashMap<Integer, BaseData>();
	}
	
	public void addBase(int baseId, BaseData baseData) 
	{
		baseDatas.put(baseId, baseData);
	}
		
	public BaseData getBase(int id) 
	{
		return baseDatas.get(id);
	}
}
