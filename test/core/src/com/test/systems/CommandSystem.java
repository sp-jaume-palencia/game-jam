package com.test.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.jmx.snmp.Timestamp;
import com.test.data.BaseState;
import com.test.model.net.CommandAction;
import com.test.model.net.CommandHistory;
import com.test.network.Network.GameActionID;
import com.test.network.Network.GameAttack;

public class CommandSystem
{
	int currentTurn;
	int playerTurn;
	
	HashMap<Integer,List<GameAttack>> attacks;
	
	public void load()
	{
		attacks = new HashMap<Integer,List<GameAttack>>();
		attacks.put(0, new ArrayList<GameAttack>());
		attacks.put(1, new ArrayList<GameAttack>());
		attacks.put(2, new ArrayList<GameAttack>());
		attacks.put(3, new ArrayList<GameAttack>());
		attacks.put(4, new ArrayList<GameAttack>());
		attacks.put(5, new ArrayList<GameAttack>());
		attacks.put(6, new ArrayList<GameAttack>());
		attacks.put(7, new ArrayList<GameAttack>());
	}
	
	public void addAttack(GameAttack ga)
	{
		attacks.get(ga.roomId).add(ga);
	}
	
	public void processAttacks(int roomId)
	{
		List<GameAttack> attcks = attacks.get(roomId);
		for(GameAttack attack : attcks)
		{
			BaseState orig = RootSystem.net.server.worldServer.rooms[roomId].mapState.baseStates.get(attack.originId);
			BaseState targ = RootSystem.net.server.worldServer.rooms[roomId].mapState.baseStates.get(attack.targetId);
			
			if(orig.numTroops - 1 > targ.numTroops)
			{
				targ.ownerId = orig.ownerId;
				targ.numTroops = orig.numTroops - 1 - targ.numTroops;
			}
			else if(orig.numTroops - 1 == targ.numTroops)
			{
				targ.numTroops = 1;
			}
			else
			{
				targ.numTroops = targ.numTroops - (targ.numTroops - 1);
			}
			
			orig.numTroops = 1;
		}
		
		attacks.clear();
	}
	
	
}
