package com.test.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network
{
	static public final int port = 54555;

	static public void register (EndPoint endPoint)
	{
		Kryo kryo = endPoint.getKryo();
		kryo.register(GameCommand.class);
		kryo.register(GameAddChangeStat.class);
		kryo.register(GameDelChangeStat.class);
		kryo.register(RoomCommand.class);
		kryo.register(RoomList.class);
		kryo.register(RoomInfo.class);
		kryo.register(RoomInfo[].class);
	}

	//CLIENT TO SERVER
	static public class GameCommand
	{
		public int gametime;
		public int objectID;
		public int objectType;
		public int actionID;
		public int value1;
		public int value2;
		public int value3;
		public int value4;
		public int value5;
	}
	
	static public class GameAddChangeStat
	{
		public int gametime;
		public int objectID;
		public int statID;
		public int value;
	}
	
	static public class GameDelChangeStat
	{
		public int gametime;
		public int objectID;
		public int statID;
	}
	
	static public class RoomCommand
	{
		public int roomID;
		public int actionID;
	}
	
	//SERVER TO CLIENT
	static public class RoomList
	{
		public RoomInfo[] rooms;
	}
	
	static public class RoomInfo
	{
		public int id;
		public int numPlayer;
	}
	
	//ENUMS
	public enum RoomActionID
	{
		GETROOMS(0),
		JOINROOM(1),
		QUITROOM(2),
		STARTGAME(3);
		
		private final int value;
	    private RoomActionID(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum GameActionID
	{
		BASEATACKBASE(0);
		
		private final int value;
	    private GameActionID(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum ObjectType
	{
		GAME(0),
		PLAYER(1),
		BASE(2),
		SQUAD(3),
		HERO(4);
		
		private final int value;
	    private ObjectType(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
}