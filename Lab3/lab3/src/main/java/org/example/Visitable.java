package org.example;
import java.time.*;
import java.util.*;
public interface Visitable {
    public Map<LocalDate, TimeInterval> getTimetable();
    public default LocalTime getOpeningHour(LocalDate date) {
        TimeInterval interval = getTimetable().get(date);
        if (interval != null) {
            return interval.getStartTime();
        }
        return null;
    }
    public default LocalTime getFinishingHour(LocalDate date){
        TimeInterval interval = getTimetable().get(date);
        if (interval != null) {
            return interval.getEndTime();
        }
        return null;
    }
}
