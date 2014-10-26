package com.test.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.test.network.Network.GameAttack;
import com.test.network.Network.GameBaseState;
import com.test.network.Network.GameStateOfGame;
import com.test.systems.RootSystem;

public class MapState
{
	public HashMap<Integer, BaseState> baseStates;
	
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
		
		baseStates = new HashMap<Integer, BaseState>();

		for(Entry<Integer, BaseData> entry : data.bases.entrySet())
		{
			addBaseState(entry.getKey(), entry.getValue());
		}
	}
	
	public void addBaseState(int baseId, BaseData baseData) 
	{
		baseStates.put(baseId, new BaseState(baseData));
	}
		
	public BaseState getBaseState(int id) 
	{
		return baseStates.get(id);
	}

	public boolean isOwnPlanet(int id)
	{
		BaseState planet = baseStates.get(id);
		PlayerState playerState = RootSystem.data.playerState;
		
		return planet.ownerId == playerState.id;
	}
	
	public void attackTo(int originId, int targetId)
	{
		if(RootSystem.data.gameState.currentPlayer == RootSystem.data.playerState.id)
		{
			RootSystem.net.client.sendAttack(originId, targetId);
		}
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
		for(GameBaseState baseState : state.bases)
		{
			BaseState bs = baseStates.get(baseState.baseId);
			bs.numTroops = baseState.numTroops;
			bs.ownerId = baseState.ownerId;
			
			baseStates.put(baseState.baseId, bs);
		}
	}

	public void addAttack(GameAttack attack)
	{
		if(attack.player != RootSystem.data.playerState.id)
		{
			// Dont add the players attack
			attackState.addAttack(attack);
		}
	}


	

}
