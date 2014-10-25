package com.test.systems;

import com.badlogic.gdx.math.Vector2;
import com.test.data.BaseData;
import com.test.data.MapData;
import com.test.data.MapState;
import com.test.game.TimeData;

public class DataSystem
{
	public MapState mapState;
	public MapData map;
	public TimeData timeData;

	public void load()
	{
		timeData = new TimeData();
		
		map = new MapData();
		// Top - Left
		map.addBase(1, new BaseData(1, 1, new Vector2(320, 381), new int[] { 2,3 }));
		map.addBase(2, new BaseData(2, 1, new Vector2(730, 380), new int[] { 1,4,9 }));
		map.addBase(3, new BaseData(3, 1, new Vector2(323, 681), new int[] { 1,4,5 }));
		map.addBase(4, new BaseData(4, 2, new Vector2(719, 684), new int[] { 2,3,5,8,9 }));
		map.addBase(5, new BaseData(5, 1, new Vector2(482, 984), new int[] { 3,4,6 }));
		map.addBase(6, new BaseData(6, 0, new Vector2(329, 1296), new int[] { 5,7,14 }));
		map.addBase(7, new BaseData(7, 0, new Vector2(815, 1266), new int[] { 6,8,12,13,14 }));
		map.addBase(8, new BaseData(8, 0, new Vector2(1013, 933), new int[] { 4,7,9,12 }));
		map.addBase(9, new BaseData(9, 1, new Vector2(1064, 507), new int[] { 2,4,8,10,12 }));
		map.addBase(10, new BaseData(10, 2, new Vector2(1300, 363), new int[] { 9,11,31 }));
		map.addBase(11, new BaseData(11, 4, new Vector2(1513, 819), new int[] { 10,12,30,31 }));
		map.addBase(12, new BaseData(12, 0, new Vector2(1298, 1251), new int[] { 7,8,9,11,13,29 }));
		map.addBase(13, new BaseData(13, 0, new Vector2(1034, 1472), new int[] { 7,12,16,17 }));
		map.addBase(14, new BaseData(14, 0, new Vector2(572, 1458), new int[] { 6,7,15,16 }));
		map.addBase(15, new BaseData(15, 0, new Vector2(245, 1662), new int[] { 14,16,18 }));
		map.addBase(16, new BaseData(16, 0, new Vector2(719, 1692), new int[] { 13,14,15,17,24 }));
		map.addBase(17, new BaseData(17, 0, new Vector2(1211, 1695), new int[] { 13,16,23,24,26,29 }));
		map.addBase(18, new BaseData(18, 0, new Vector2(386, 1977), new int[] { 15,19,21 }));
		map.addBase(19, new BaseData(19, 0, new Vector2(236, 2286), new int[] { 18,20,21 }));
		map.addBase(20, new BaseData(20, 2, new Vector2(224, 2610), new int[] { 19,22 }));
		map.addBase(21, new BaseData(21, 0, new Vector2(626, 2280), new int[] { 18,19,22,23,24 }));
		map.addBase(22, new BaseData(22, 0, new Vector2(632, 2586), new int[] { 20,21,23 }));
		map.addBase(23, new BaseData(23, 0, new Vector2(953, 2439), new int[] { 21,22,24,25,17 }));
		map.addBase(24, new BaseData(24, 0, new Vector2(914, 2025), new int[] { 16,17,21,23 }));
		map.addBase(25, new BaseData(25, 0, new Vector2(1223, 2592), new int[] { 23,26,27 }));
		map.addBase(26, new BaseData(26, 0, new Vector2(1481, 2130), new int[] { 17,25,27,28 }));
		map.addBase(27, new BaseData(27, 3, new Vector2(1744, 2592), new int[] { 25,26,37 }));
		map.addBase(28, new BaseData(28, 1, new Vector2(1729, 1686), new int[] { 26,29,34,40,36,37 }));
		map.addBase(29, new BaseData(29, 0, new Vector2(1496, 1470), new int[] { 12,17,28,30 }));
		map.addBase(30, new BaseData(30, 3, new Vector2(1753, 1248), new int[] { 11,32,33,35,34,29 }));
		map.addBase(31, new BaseData(31, 0, new Vector2(1759, 348), new int[] { 10,11,32 }));
		map.addBase(32, new BaseData(32, 1, new Vector2(1987, 495), new int[] { 31,30,33,39,38 }));
		map.addBase(33, new BaseData(33, 2, new Vector2(2038, 915), new int[] { 30,32,35,39 }));
		map.addBase(34, new BaseData(34, 0, new Vector2(1987, 1449), new int[] { 28,30,35,40 }));
		map.addBase(35, new BaseData(35, 0, new Vector2(2248, 1254), new int[] { 30,33,34,46,51 }));
		map.addBase(36, new BaseData(36, 0, new Vector2(2035, 2019), new int[] { 28,40,41,37 }));
		map.addBase(37, new BaseData(37, 4, new Vector2(1981, 2442), new int[] { 27,28,36,41,42 }));
		map.addBase(38, new BaseData(38, 3, new Vector2(2326, 348), new int[] { 32,39,43 }));
		map.addBase(39, new BaseData(39, 3, new Vector2(2329, 660), new int[] { 33,32,38,44,45 }));
		map.addBase(40, new BaseData(40, 2, new Vector2(2221, 1680), new int[] { 36,28,34,51,47 }));
		map.addBase(41, new BaseData(41, 0, new Vector2(2335, 2271), new int[] { 36,37,42,49,48 }));
		map.addBase(42, new BaseData(42, 5, new Vector2(2326, 2592), new int[] { 37,41,50 }));
		map.addBase(43, new BaseData(43, 3, new Vector2(2728, 351), new int[] { 38,44 }));
		map.addBase(44, new BaseData(44, 3, new Vector2(2728, 660), new int[] { 43,39,45 }));
		map.addBase(45, new BaseData(45, 4, new Vector2(2566, 975), new int[] { 39,44,46 }));
		map.addBase(46, new BaseData(46, 0, new Vector2(2719, 1287), new int[] { 45,35,51 }));
		map.addBase(47, new BaseData(47, 5, new Vector2(2722, 1644), new int[] { 48,40,51 }));
		map.addBase(48, new BaseData(48, 0, new Vector2(2569, 1968), new int[] { 47,49,41 }));
		map.addBase(49, new BaseData(49, 0, new Vector2(2719, 2274), new int[] { 48,41,50 }));
		map.addBase(50, new BaseData(50, 4, new Vector2(2731, 2580), new int[] { 49,42 }));
		map.addBase(51, new BaseData(51, 1, new Vector2(2449, 1470), new int[] { 35,46,40,47 }));
		
		mapState = new MapState(map);

		mapState.attackState.addAttack(30, 11);
		mapState.attackState.addAttack(27, 37);
		mapState.attackState.addAttack(40, 28);
		mapState.attackState.addAttack(44, 45);
		mapState.attackState.addAttack(47, 51);
		mapState.attackState.addAttack(38, 32);
		mapState.attackState.addAttack(50, 42);
		mapState.attackState.addAttack(39, 33);
		mapState.attackState.addAttack(9, 10);
		mapState.attackState.addAttack(3, 4);	
	}

}
