package org.example;

import java.time.*;
import java.util.*;

public class Church extends Attraction implements Visitable {
    private final Map<LocalDate, TimeInterval> timetable;

    public Church(String name, Map<LocalDate, TimeInterval> timetable) {
        super(name);
        this.timetable = timetable;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }

}