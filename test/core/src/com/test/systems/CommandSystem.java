package com.test.systems;

import com.test.model.net.CommandAction;
import com.test.model.net.CommandHistory;

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

}