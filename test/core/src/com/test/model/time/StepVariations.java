package com.test.model.time;

public class StepVariations extends TimeVariations {
    public int getValue(int time)  {
        return timeMap.floorEntry(time).getValue();
    }
}