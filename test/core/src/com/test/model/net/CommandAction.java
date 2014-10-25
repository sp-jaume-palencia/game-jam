package com.test.model.net;

import java.util.List;

import com.test.network.Network.GameActionID;

public class CommandAction
{
	public enum statType
	{
		HEALTH,
		POWER,
		DEFENSE
	}
	
	public int actorId;
	public int targetId;
	public GameActionID actionId;
	public int time;
	
	public List<Integer> statsAffected;
	public List<Integer> statsModifier;
	
	public CommandAction(GameActionID baseatackbase, int actorId, int targetId)
	{
		this.actionId = baseatackbase;
		this.actorId = actorId;
		this.targetId = targetId;
	}
	
	public void addAffected(Integer statAffected, Integer statModifier)
	{
		statsAffected.add(statAffected);
		statsModifier.add(statModifier);
	}
}
