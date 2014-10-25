package com.test.model.time;

import java.util.Map;

public class InterpolatedVariations extends TimeVariations {
    @Override
    public int getValue(int time)  {
        Map.Entry<Integer,Integer> before = timeMap.ceilingEntry(time);
        Map.Entry<Integer,Integer> after = timeMap.floorEntry(time);
        if (before == null) return after.getValue();
        if (after == null) return before.getValue();
        int variationPerTimeUnit = (after.getValue() - before.getValue()) / (after.getKey() - before.getKey());
        int interpolation = before.getValue() + (time-before.getKey())*variationPerTimeUnit;
        return interpolation;
    }
}