package com.test.model.time;

public class DiscreteVariations extends TimeVariations {
    @Override
    public int getValue(int time)  {
        return timeMap.get(time);
    }
}