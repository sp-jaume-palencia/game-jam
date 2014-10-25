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
		
	}
	
	public void addCommand(CommandAction cmd)
	{
		history.addCommandAction(cmd);
	}
	
	public void sendAttack(int originId, int targetId, int scrollTime, GameActionID actionId)
	{
		GameCommand cmd = new GameCommand();
		cmd.actionID = actionId.getValue();
		cmd.gametime = scrollTime + getTimestamp();
		cmd.objectID = originId;
		cmd.value1 = targetId;
	}

	private long getTimestamp()
	{
		java.util.Date date = new java.util.Date();
		return date.getTime();
	}
}
