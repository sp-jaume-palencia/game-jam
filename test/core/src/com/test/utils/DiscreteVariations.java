package com.test.utils;

public class DiscreteVariations extends TimeVariations {
    public int getValue(int time)  {
        return timeMap.get(time);
    }
}