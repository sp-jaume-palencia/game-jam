package com.test.data;

import java.util.HashMap;

import com.badlogic.gdx.utils.Array;
import com.test.network.Network.GameAttack;

public class AttackState
{
	public Array<GameAttack> attacks;
	
	public AttackState()
	{
		attacks = new Array<GameAttack>();
	}
	
	public void addAttack(GameAttack attack)
	{
		attacks.add(attack);
	}
	
}
