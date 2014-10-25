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
		timeData.addBase(1, new Vector2(320, 381), new int[] { 2,3 });
		timeData.addBase(2, new Vector2(730, 380), new int[] { 1,4,9 });
		timeData.addBase(3, new Vector2(323, 681), new int[] { 1,4,5 });
		timeData.addBase(4, new Vector2(719, 684), new int[] { 2,3,5,8,9 });
		timeData.addBase(5, new Vector2(482, 984), new int[] { 3,4,6 });
		timeData.addBase(6, new Vector2(329, 1296), new int[] { 5,7,14 });
		timeData.addBase(7, new Vector2(815, 1266), new int[] { 6,8,12,13,14 });
		timeData.addBase(8, new Vector2(1013, 933), new int[] { 4,7,9,12 });
		timeData.addBase(9, new Vector2(1064, 507), new int[] { 2,4,8,10,12 });
		timeData.addBase(10, new Vector2(1300, 363), new int[] { 9,11,31 });
		timeData.addBase(11, new Vector2(1513, 819), new int[] { 10,12,30,31 });
		timeData.addBase(12, new Vector2(1298, 1251), new int[] { 7,8,9,11,13,29 });
		timeData.addBase(13, new Vector2(1034, 1472), new int[] { 7,12,16,17 });
		timeData.addBase(14, new Vector2(572, 1458), new int[] { 6,7,15,16 });
		timeData.addBase(15, new Vector2(245, 1662), new int[] { 14,16,18 });
		timeData.addBase(16, new Vector2(719, 1692), new int[] { 13,14,15,17,24 });
		timeData.addBase(17, new Vector2(1211, 1695), new int[] { 13,16,23,24,26,29 });
		timeData.addBase(18, new Vector2(386, 1977), new int[] { 15,19,21 });
		timeData.addBase(19, new Vector2(236, 2286), new int[] { 18,20,21 });
		timeData.addBase(20, new Vector2(224, 2610), new int[] { 19,22 });
		timeData.addBase(21, new Vector2(626, 2280), new int[] { 18,19,22,23,24 });
		timeData.addBase(22, new Vector2(632, 2586), new int[] { 20,21,23 });
		timeData.addBase(23, new Vector2(953, 2439), new int[] { 21,22,24,25,17 });
		timeData.addBase(24, new Vector2(914, 2025), new int[] { 16,17,21,23 });
		timeData.addBase(25, new Vector2(1223, 2592), new int[] { 23,26,27 });
		timeData.addBase(26, new Vector2(1481, 2130), new int[] { 17,25,27,28 });
		timeData.addBase(27, new Vector2(1744, 2592), new int[] { 25,26,37 });
		timeData.addBase(28, new Vector2(1729, 1686), new int[] { 26,29,34,40,36,37 });
		timeData.addBase(29, new Vector2(1496, 1470), new int[] { 12,17,28,30 });
		timeData.addBase(30, new Vector2(1753, 1248), new int[] { 11,32,33,35,34,29 });
		timeData.addBase(31, new Vector2(1759, 348), new int[] { 10,11,32 });
		timeData.addBase(32, new Vector2(1987, 495), new int[] { 31,30,33,39,38 });
		timeData.addBase(33, new Vector2(2038, 915), new int[] { 30,32,35,39 });
		timeData.addBase(34, new Vector2(1987, 1449), new int[] { 28,30,35,40 });
		timeData.addBase(35, new Vector2(2248, 1254), new int[] { 30,33,34,46,51 });
		timeData.addBase(36, new Vector2(2035, 2019), new int[] { 28,40,41,37 });
		timeData.addBase(37, new Vector2(1981, 2442), new int[] { 27,28,36,41,42 });
		timeData.addBase(38, new Vector2(2326, 348), new int[] { 32,39,43 });
		timeData.addBase(39, new Vector2(2329, 660), new int[] { 33,32,38,44,45 });
		timeData.addBase(40, new Vector2(2221, 1680), new int[] { 36,28,34,51,47 });
		timeData.addBase(41, new Vector2(2335, 2271), new int[] { 36,37,42,49,48 });
		timeData.addBase(42, new Vector2(2326, 2592), new int[] { 37,41,50 });
		timeData.addBase(43, new Vector2(2728, 351), new int[] { 38,44 });
		timeData.addBase(44, new Vector2(2728, 660), new int[] { 43,39,45 });
		timeData.addBase(45, new Vector2(2566, 975), new int[] { 39,44,46 });
		timeData.addBase(46, new Vector2(2719, 1287), new int[] { 45,35,51 });
		timeData.addBase(47, new Vector2(2722, 1644), new int[] { 48,40,51 });
		timeData.addBase(48, new Vector2(2569, 1968), new int[] { 47,49,41 });
		timeData.addBase(49, new Vector2(2719, 2274), new int[] { 48,41,50 });
		timeData.addBase(50, new Vector2(2731, 2580), new int[] { 49,42 });
		timeData.addBase(51, new Vector2(2449, 1470), new int[] { 35,46,40,47 });
		
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
