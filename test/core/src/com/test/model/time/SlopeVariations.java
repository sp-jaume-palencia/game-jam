package com.test.model.time;

import java.util.Map;

public class SlopeVariations extends TimeVariations {
    public InterpolatedVariations getInterpolatedVariations() {
        InterpolatedVariations interpolated = new InterpolatedVariations();
        int time = 0;
        int value = 0;
        for (Map.Entry<Integer,Integer> slopeChange : timeMap.entrySet())
        {
        	Integer sv = slopeChange.getValue();
        	Integer sk = slopeChange.getKey();
            value = value + sv*(sk-time);
            time = sk;
            if (value < 0) {
                time = value/sk;
                value = 0;
                interpolated.put(time, value);
                return interpolated;
            }
            interpolated.put(time, value);
        }
        return interpolated;
    }
}