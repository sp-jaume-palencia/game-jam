package com.test.utils;

public class StepVariations extends TimeVariations {
    public int getValue(int time)  {
        return timeMap.floorEntry(time).getValue();
    }
}