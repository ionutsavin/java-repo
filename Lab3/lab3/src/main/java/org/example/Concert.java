package org.example;

import java.time.*;
import java.util.*;

public class Concert extends Attraction implements Visitable, Payable {

    private final Map<LocalDate, TimeInterval> timetable;
    private final double ticketPrice;

    public Concert(String name, double ticketPrice, Map<LocalDate, TimeInterval> timetable) {
        super(name);
        this.ticketPrice = ticketPrice;
        this.timetable = timetable;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }
}
