package com.test.data;

import java.util.List;

import com.test.network.Network.GameAttack;
import com.test.network.Network.GameStateOfGame;

public class MapState
{
	public MapData mapData;
	public PlayerState[] players;
	public AttackState attackState;
	
	public MapState(MapData data)
	{
		mapData = data;
		players = new PlayerState[4];
		players[0] = new PlayerState(1, 1400, 234);
		players[1] = new PlayerState(2, 1300, 631);
		players[2] = new PlayerState(3,  900, 512);
		players[3] = new PlayerState(4,  200, 123);
		
		attackState = new AttackState();
	}
	
	public PlayerState getPlayerState(int id)
	{
		for(PlayerState state : players)
		{
			if(state.id == id)
			{
				return state;
			}
		}
		
		return null;
	}

	public void process(List<GameAttack> attacks)
	{
		// TODO Auto-generated method stub
		
	}

	public void process(GameStateOfGame state)
	{
		// TODO Auto-generated method stub
		
	}

	public void addAttack(GameAttack attack)
	{
		// TODO Auto-generated method stub
		
	}

}
