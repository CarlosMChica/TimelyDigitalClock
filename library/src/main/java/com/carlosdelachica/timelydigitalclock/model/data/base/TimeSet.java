package com.carlosdelachica.timelydigitalclock.model.data.base;

public class TimeSet {
    private TimeUnitSet seconds, minutes, hours;

    public TimeSet(TimeUnitSet seconds, TimeUnitSet minutes, TimeUnitSet hours) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
    }

    public TimeUnitSet getSeconds() {
        return seconds;
    }

    public TimeUnitSet getMinutes() {
        return minutes;
    }

    public TimeUnitSet getHours() {
        return hours;
    }


}
