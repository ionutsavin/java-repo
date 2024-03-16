package org.example;

import java.time.LocalDate;
import java.util.*;

public class TravelPlan {
    private final Trip trip;
    private final List<DayPlan> dayPlans;
    private final Set<String> visitedAttractions;

    public TravelPlan(Trip trip) {
        this.trip = trip;
        this.dayPlans = new ArrayList<>();
        this.visitedAttractions = new HashSet<>();
    }

    public void generatePlan() {
        LocalDate currentDate = trip.getStartDate();
        while (!currentDate.isAfter(trip.getEndDate())) {
            DayPlan dayPlan = new DayPlan(currentDate);
            addAttractionToDayPlan(dayPlan);
            dayPlans.add(dayPlan);
            currentDate = currentDate.plusDays(1);
        }
    }

    private void addAttractionToDayPlan(DayPlan dayPlan) {
        LocalDate currentDate = dayPlan.getDate();
        for (Attraction attraction : trip.getAttractions()) {
            String attractionName = attraction.getName();
            if (!visitedAttractions.contains(attractionName))
                if (attraction instanceof Visitable && ((Visitable) attraction).getTimetable().containsKey(currentDate)) {
                    dayPlan.addAttraction(attraction);
                    visitedAttractions.add(attractionName);
                    break;
                }
        }
    }

    public void printPlan() {
        for (DayPlan dayPlan : dayPlans) {
            System.out.println("Date: " + dayPlan.getDate());
            System.out.println("Attraction:");
            dayPlan.printAttractions();
            System.out.println();
        }
    }
}
