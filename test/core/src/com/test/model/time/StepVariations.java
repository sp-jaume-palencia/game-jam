package com.test.model.time;

public class StepVariations extends TimeVariations {
    @Override
    public int getValue(int time)  {
        return timeMap.floorEntry(new Integer(time)).getValue();
    }
}