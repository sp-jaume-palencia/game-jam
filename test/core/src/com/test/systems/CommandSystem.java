package com.test.systems;

import com.test.model.net.CommandAction;
import com.test.model.net.CommandHistory;

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

}
