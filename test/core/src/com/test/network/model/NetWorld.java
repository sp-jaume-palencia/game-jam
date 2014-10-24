package com.test.network.model;

import com.test.network.Network.RoomInfo;

public class NetWorld
{
	NetRoom[] rooms;

	public NetWorld()
	{
		rooms = new NetRoom[5];
		
		rooms[0] = new NetRoom(0);
		rooms[1] = new NetRoom(1);
		rooms[2] = new NetRoom(2);
		rooms[3] = new NetRoom(3);
		rooms[4] = new NetRoom(4);
				
	}
	
	public void playerJoinRoom(int roomId, int playerId)
	{
		rooms[roomId].addPlayer(playerId);
	}
	
	public void playerQuitRoom(int roomId, int playerId)
	{
		
	}

	public RoomInfo[] getRooms()
	{
		RoomInfo[] infos = new RoomInfo[]{new RoomInfo(),new RoomInfo(),new RoomInfo(),new RoomInfo(),new RoomInfo()};
		infos[0].id = 0;
		infos[0].numPlayer = rooms[0].numPlayers;
		infos[1].id = 1;
		infos[1].numPlayer = rooms[1].numPlayers;
		infos[2].id = 2;
		infos[2].numPlayer = rooms[2].numPlayers;
		infos[3].id = 3;
		infos[3].numPlayer = rooms[3].numPlayers;
		infos[4].id = 4;
		infos[4].numPlayer = rooms[4].numPlayers;
		
		return infos;
	}

	public boolean roomIsReady(int roomId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void startGame(int roomId) {
		// TODO Auto-generated method stub
		
	}
}
