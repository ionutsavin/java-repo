package org.example;

import java.time.LocalDate;
import java.util.Map;

public class Statue extends Attraction implements Visitable {

    private final Map<LocalDate, TimeInterval> timetable;
    public Statue(String name, Map<LocalDate, TimeInterval> timetable){
        super(name);
        this.timetable = timetable;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }
}