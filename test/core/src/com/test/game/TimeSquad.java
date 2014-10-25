package com.test.game;

import java.util.Map;

import com.test.model.time.InterpolatedVariations;
import com.test.model.time.StepVariations;
import com.test.utils.Update;

public class TimeSquad extends TimeActor {
	public StepVariations upgrade;
	public InterpolatedVariations attack;
	public InterpolatedVariations life;
	public StepVariations base;
	public InterpolatedVariations posX;
	public InterpolatedVariations posY;

	public TimeSquad() {};
	
	public void update(Map<String,Update> map) {
		super.update(map);
		upgrade.update(map.get("upgrade"));
		attack.update(map.get("attack"));
		life.update(map.get("life"));
		base.update(map.get("base"));
		posX.update(map.get("posX"));
		posY.update(map.get("posY"));
	}
	
	public int getAttack(int time) {
		return attack.getValue(time);
	}
	
	public int getLife(int time) {
		return life.getValue(time);
	}
	
	public int getRotation(int time) {
        Map.Entry<Integer,Integer> xBefore = posX.timeMap.ceilingEntry(time);
        Map.Entry<Integer,Integer> xAfter = posX.timeMap.floorEntry(time);
        int xVariationPerTimeUnit = (xAfter.getValue() - xBefore.getValue()) / (xAfter.getKey() - xBefore.getKey());
        
        Map.Entry<Integer,Integer> yBefore = posY.timeMap.ceilingEntry(time);
        Map.Entry<Integer,Integer> yAfter = posY.timeMap.floorEntry(time);
        int yVariationPerTimeUnit = (yAfter.getValue() - yBefore.getValue()) / (yAfter.getKey() - yBefore.getKey());
        
        if        (xVariationPerTimeUnit > 0 && yVariationPerTimeUnit > 0)   return (int) (Math.atan(Math.abs(yVariationPerTimeUnit)/Math.abs(xVariationPerTimeUnit))*2*Math.PI/360.0)    ;
        else   if (xVariationPerTimeUnit > 0 && yVariationPerTimeUnit < 0)   return (int) (Math.atan(Math.abs(yVariationPerTimeUnit)/Math.abs(xVariationPerTimeUnit))*2*Math.PI/360.0)+ 90;
        else   if (xVariationPerTimeUnit < 0 && yVariationPerTimeUnit < 0)   return (int) (Math.atan(Math.abs(yVariationPerTimeUnit)/Math.abs(xVariationPerTimeUnit))*2*Math.PI/360.0)+180;
        else /*if (xVariationPerTimeUnit < 0 && yVariationPerTimeUnit > 0)*/ return (int) (Math.atan(Math.abs(yVariationPerTimeUnit)/Math.abs(xVariationPerTimeUnit))*2*Math.PI/360.0)+270;
	}
}
