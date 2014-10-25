package com.test.data;

import java.util.HashMap;

public class AttackState
{
	public HashMap<Integer, Integer> attacks;
	
	public AttackState()
	{
		attacks = new HashMap<Integer, Integer>();
	}
	
	public void addAttack(int originId, int targetId)
	{
		attacks.put(originId, targetId);
	}	
	
	public Integer getAttackingBaseId(int id)
	{
		return attacks.containsKey(id)? attacks.get(id) : null;
	}
}
