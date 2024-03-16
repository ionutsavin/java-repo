package org.example;

import java.time.LocalDate;
import java.util.*;

public class DayPlan {
    private final LocalDate date;
    private final List<Attraction> attractions;

    public DayPlan(LocalDate date) {
        this.date = date;
        this.attractions = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public void printAttractions() {
        for (Attraction attraction : attractions) {
            System.out.println(attraction.getName());
            System.out.println("Opening hour: " + ((Visitable) attraction).getOpeningHour(date));
            System.out.println("Finishing hour: " + ((Visitable) attraction).getFinishingHour(date));
        }
    }
}
