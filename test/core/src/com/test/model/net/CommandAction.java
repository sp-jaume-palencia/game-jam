package com.test.model.net;

import java.util.List;

public class CommandAction
{
	public enum ActionId
	{
		ATTACK,
		CONSTRUCT
	}
	
	public enum statType
	{
		HEALTH,
		POWER,
		DEFENSE
	}
	
	public int actorId;
	public int targetId;
	public ActionId actionId;
	public int time;
	
	public List<Integer> statsAffected;
	public List<Integer> statsModifier;
	
	public CommandAction(int actorId, int targetId, ActionId actionId)
	{
		this.actorId = actorId;
		this.actionId = actionId;
	}
	
	public void addAffected(Integer statAffected, Integer statModifier)
	{
		statsAffected.add(statAffected);
		statsModifier.add(statModifier);
	}
}
