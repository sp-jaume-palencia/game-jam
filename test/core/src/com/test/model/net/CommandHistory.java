package com.test.model.net;

import java.util.HashMap;
import java.util.TreeMap;

public class CommandHistory
{
	HashMap<Integer, TreeMap<Integer, CommandAction>> actionHistory;
	
	public CommandHistory()
	{
		actionHistory = new HashMap<Integer, TreeMap<Integer, CommandAction>>();
	}
	
	public void addCommandAction(CommandAction action)
	{
		TreeMap<Integer, CommandAction> tree = new TreeMap<Integer, CommandAction>();
		tree.put(action.time, action);
		actionHistory.put(action.actorId, tree);
	}
	
}
