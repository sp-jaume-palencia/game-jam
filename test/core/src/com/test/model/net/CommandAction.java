package com.test.model.net;

import java.util.List;

public class CommandAction
{
	public enum ActionId
	{
		ATTACK,
		CONSTRUCT
	}
	
	public int actorId;
	public List<Integer> affectedIds;	
	public ActionId actionId;
	public int time;
	
	public CommandAction(int actorId, ActionId actionId)
	{
		this.actorId = actorId;
		this.actionId = actionId;
	}
	
	public void addAffected(int affectedId)
	{
		affectedIds.add(affectedId);
	}
}
