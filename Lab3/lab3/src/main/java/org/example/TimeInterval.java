package org.example;

import java.time.LocalTime;

public class TimeInterval extends Pair<LocalTime, LocalTime> {

    public TimeInterval(LocalTime startTime, LocalTime endTime) {
        super(startTime, endTime);
    }

    public LocalTime getStartTime() {
        return this.getFirst();
    }

    public LocalTime getEndTime() {
        return this.getSecond();
    }
}
