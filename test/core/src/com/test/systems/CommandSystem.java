package com.test.systems;

import com.sun.jmx.snmp.Timestamp;
import com.test.model.net.CommandAction;
import com.test.model.net.CommandHistory;
import com.test.network.Network.GameActionID;
import com.test.network.Network.GameCommand;

public class CommandSystem
{
	CommandHistory history;
	
	public void load()
	{
		history = new CommandHistory();
	}
	
	public void addCommand(CommandAction cmd)
	{
		history.addCommandAction(cmd);
		RootSystem.net.server.sendAddChangeStat(cmd.time, cmd.actorId, 0, -1);
		//update();
	}
	
	public void update()
	{
		
		//RootSystem.net.server.sendAddChangeStat();
		//RootSystem.net.server.sendDelChangeStat();
	}
	
	public void sendAttack(int originId, int targetId, int scrollTime, GameActionID actionId)
	{
		GameCommand cmd = new GameCommand();
		cmd.actionID = actionId.getValue();
		cmd.gametime = scrollTime;
		cmd.objectID = originId;
		cmd.value1 = targetId;
	}
}
