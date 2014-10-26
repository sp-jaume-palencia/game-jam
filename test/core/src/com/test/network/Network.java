package com.test.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network
{
	static public final int port = 54555;

	static public void register (EndPoint endPoint)
	{
		Kryo kryo = endPoint.getKryo();
		kryo.register(GameAttack.class);
		kryo.register(RoomCommand.class);
		kryo.register(RoomList.class);
		kryo.register(RoomInfo.class);
		kryo.register(RoomInfo[].class);
		kryo.register(GameYourTurn.class);
		kryo.register(GameEndOfTurn.class);
		kryo.register(GameFinish.class);
		kryo.register(GameStateOfGame.class);
		kryo.register(GameBaseState.class);
		kryo.register(GameBaseState[].class);
	}

	//CLIENT TO SERVER
	static public class GameAttack
	{
		public int roomId;
		public long player;
		public long gameTurn;
		public int originId;
		public int targetId;
	}
	
	static public class RoomCommand
	{
		public long player;
		public int roomId;
		public int actionId;
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
	
	static public class GameYourTurn
	{
		public int turn;
		public int player;
	}
	
	static public class GameEndOfTurn
	{
		public int turn;
		public int player;
	}
	
	static public class GameFinish
	{
		public int player;
	}
	
	static public class GameStateOfGame
	{
		public GameBaseState[] bases;
	}
	
	static public class GameBaseState
	{
		public int baseId;
		public int ownerId;
		public int numTroops;
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