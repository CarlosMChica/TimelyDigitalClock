package com.carlosdelachica.timelydigitalclock.model.data.base;

public class TimeUnitSet {

    private TimeUnitType timeUnitType;
    private int actualValueUnit = 0;
    private int actualValueTens = 0;

    public TimeUnitSet(TimeUnitType timeUnitType) {
        this.timeUnitType = timeUnitType;
    }

    public TimeUnitType getTimeUnitType() {
        return timeUnitType;
    }

    public int getActualValueUnit() {
        return actualValueUnit;
    }

    public int getActualValueTens() {
        return actualValueTens;
    }

    public void updateValues(int actualValueUnit, int actualValueTens) {
        this.actualValueUnit = actualValueUnit;
        this.actualValueTens = actualValueTens;
    }
}
