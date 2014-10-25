package com.test.systems;

import java.util.List;

import com.sun.jmx.snmp.Timestamp;
import com.test.model.net.CommandAction;
import com.test.model.net.CommandHistory;
import com.test.network.Network.GameActionID;
import com.test.network.Network.GameAttack;

public class CommandSystem
{
	CommandHistory history;
	
	int currentTurn;
	int playerTurn;
	
	List<GameAttack> attacks;
	
	public void load()
	{
		history = new CommandHistory();
	}
	
	public void nextTurn()
	{
		currentTurn++;
	}
	
	public void nextPlayer()
	{
		//update model
		RootSystem.data.mapState.process(attacks);
		//parse mapState
		RootSystem.net.server.sendNewState();
		attacks.clear();
		playerTurn++;
	}
	
	public void addAttack(GameAttack ga)
	{
		attacks.add(ga);
	}
	
	
}
