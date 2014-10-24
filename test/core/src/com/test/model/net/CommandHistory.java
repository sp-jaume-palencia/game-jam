package com.test.model.net;

import java.util.HashMap;
import java.util.TreeMap;

public class CommandHistory
{
	HashMap<Integer, TreeMap<Integer, CommandAction>> actionHistory;
	
	public void addCommandAction(CommandAction action)
	{
		actionHistory.get(action.actorId).put(action.time, action);
	}
	
}
