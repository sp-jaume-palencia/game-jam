package com.test.data;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.test.systems.RootSystem;

public class MapData
{
	public HashMap<Integer, BaseData> bases;
	
	public MapData()
	{
		bases = new HashMap<Integer, BaseData>();
		
		addBase( 1, new BaseData( 1, new Vector2( 225,  118), new int[]{2,3},         1, RootSystem.assets.planet7));
		addBase( 2, new BaseData( 2, new Vector2( 242,  376), new int[]{1,4,11},      1, RootSystem.assets.planet1));
		addBase( 3, new BaseData( 3, new Vector2( 565,  148), new int[]{1,4,6},       1, RootSystem.assets.planet0));
		addBase( 4, new BaseData( 4, new Vector2( 539,  394), new int[]{2,3,5},       1, RootSystem.assets.planet2));
		addBase( 5, new BaseData( 5, new Vector2( 628,  632), new int[]{4,7,14,16},   1, RootSystem.assets.planet4));
		addBase( 6, new BaseData( 6, new Vector2( 815,  266), new int[]{3,7,8},       2, RootSystem.assets.planet6));
		addBase( 7, new BaseData( 7, new Vector2( 859,  536), new int[]{5,6,9,10},    2, RootSystem.assets.planet3));
		addBase( 8, new BaseData( 8, new Vector2(1066,  118), new int[]{6,9},         2, RootSystem.assets.planet1));
		addBase( 9, new BaseData( 9, new Vector2(1113,  408), new int[]{7,8,10},      2, RootSystem.assets.planet5));
		addBase(10, new BaseData(10, new Vector2(1066,  760), new int[]{7,9,18,21},   2, RootSystem.assets.planet4));
		addBase(11, new BaseData(11, new Vector2(  76,  586), new int[]{2,12,14},     3, RootSystem.assets.planet6));
		addBase(12, new BaseData(12, new Vector2(  97,  896), new int[]{11,13,14},    3, RootSystem.assets.planet9));
		addBase(13, new BaseData(13, new Vector2( 161, 1175), new int[]{12,15,24,26}, 3, RootSystem.assets.planet5));
		addBase(14, new BaseData(14, new Vector2( 353,  735), new int[]{5,11,12,15,16},     3, RootSystem.assets.planet8));
		addBase(15, new BaseData(15, new Vector2( 370, 1024), new int[]{13,14,16,17}, 3, RootSystem.assets.planet4));
		addBase(16, new BaseData(16, new Vector2( 579,  896), new int[]{5,15,18},     3, RootSystem.assets.planet5));
		addBase(17, new BaseData(17, new Vector2( 550, 1239), new int[]{15,18,19,26}, 3, RootSystem.assets.planet6));
		addBase(18, new BaseData(18, new Vector2( 840, 1024), new int[]{10,16,17,19,21},    4, RootSystem.assets.planet3));
		addBase(19, new BaseData(19, new Vector2( 859, 1324), new int[]{17,18,20,21,22,28}, 4, RootSystem.assets.planet2));
		addBase(20, new BaseData(20, new Vector2( 859, 1630), new int[]{19,28,23},    4, RootSystem.assets.planet7));
		addBase(21, new BaseData(21, new Vector2(1113, 1148), new int[]{10,18,19,22}, 4, RootSystem.assets.planet8));
		addBase(22, new BaseData(22, new Vector2(1113, 1494), new int[]{19,21,23},    4, RootSystem.assets.planet9));
		addBase(23, new BaseData(23, new Vector2(1049, 1766), new int[]{20,22},       4, RootSystem.assets.planet4));
		addBase(24, new BaseData(24, new Vector2(  76, 1502), new int[]{13,25,26},    5, RootSystem.assets.planet0));
		addBase(25, new BaseData(25, new Vector2( 178, 1795), new int[]{24,26,27},    5, RootSystem.assets.planet4));
		addBase(26, new BaseData(26, new Vector2( 373, 1411), new int[]{13,17,24,25,28},    5, RootSystem.assets.planet2));
		addBase(27, new BaseData(27, new Vector2( 451, 1795), new int[]{25,28},       5, RootSystem.assets.planet1));
		addBase(28, new BaseData(28, new Vector2( 614, 1588), new int[]{19,20,26,27}, 5, RootSystem.assets.planet0));
	}
	
	public void addBase(Integer baseId, BaseData base)
	{
		bases.put(baseId, base);
	}
	
	public BaseData getBase(Integer baseId)
	{
		return bases.get(baseId);
	}
	
	public boolean areConnected(int originBaseId, int base2Id)
	{
		BaseData destinationBase = bases.get(base2Id);
		
		for(int annexedBaseId : destinationBase.annexedBases)
		{
			if(annexedBaseId == originBaseId)
			{
				return true;
			}
		}
		
		return false;
	}

}
