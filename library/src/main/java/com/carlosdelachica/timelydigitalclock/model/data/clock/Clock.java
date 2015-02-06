package com.carlosdelachica.timelydigitalclock.model.data.clock;

import com.carlosdelachica.timelydigitalclock.model.data.base.TimeSet;
import com.carlosdelachica.timelydigitalclock.model.data.base.TimeUnitSet;
import com.carlosdelachica.timelydigitalclock.model.data.base.TimeUnitType;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private Timer timer = new Timer();
    private ClockMode clockMode;
    private ClockCallback callback;

    public Clock(ClockCallback callback) {
        this(ClockMode.FORMAT_24, callback);
    }

    public Clock(ClockMode clockMode, ClockCallback callback) {
        this.clockMode = clockMode;
        this.callback = callback;
        startTime();
    }

    private void startTime() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(clockMode == ClockMode.FORMAT_24 ? Calendar.HOUR_OF_DAY : Calendar.HOUR);
                int hourUnit = hour % 10;
                int hourTens = hour / 10;
                int minute = calendar.get(Calendar.MINUTE);
                int minuteUnit = minute % 10;
                int minuteTens = minute / 10;
                int second = calendar.get(Calendar.SECOND);
                int secondUnit = second % 10;
                int secondTens = second / 10;

                TimeUnitSet seconds, minutes, hours;

                seconds = new TimeUnitSet(TimeUnitType.SECONDS);
                minutes = new TimeUnitSet(TimeUnitType.MINUTES);
                hours = new TimeUnitSet(TimeUnitType.HOURS);

                seconds.updateValues(secondUnit, secondTens);
                minutes.updateValues(minuteUnit, minuteTens);
                hours.updateValues(hourUnit, hourTens);

                callback.onTimeUpdated(new TimeSet(seconds, minutes, hours));

            }
        }, 0, 1000);
    }

    public interface ClockCallback {
        public void onTimeUpdated(TimeSet timeSet);
    }

    public enum ClockMode {
        FORMAT_24,
        FORMAT_12
    }
}
