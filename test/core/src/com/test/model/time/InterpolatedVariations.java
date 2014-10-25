package com.test.model.time;

import java.util.Map;

public class InterpolatedVariations extends TimeVariations {
    @Override
    public int getValue(int time)  {
        Map.Entry<Integer,Integer> after = timeMap.ceilingEntry(new Integer(time));
        Map.Entry<Integer,Integer> before = timeMap.floorEntry(new Integer(time));
        Integer av = after.getValue();
        Integer bv = before.getValue();
        Integer ak = after.getKey();
        Integer bk = before.getKey();
        if(ak == bk) return av; 
        int variationPerTimeUnit = (av - bv) / (ak - bk);
        int interpolation = bv + (time-bk)*variationPerTimeUnit;
        return interpolation;
    }
}