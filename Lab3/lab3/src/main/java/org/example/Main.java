package org.example;

import java.time.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Trip trip = new Trip("Paris", LocalTime.of(8, 0), LocalTime.of(23, 0));

        Map<LocalDate, TimeInterval> statueTimeTable = new HashMap<>();
        statueTimeTable.put(LocalDate.of(2024, 3, 15), new TimeInterval(LocalTime.of(8, 0), LocalTime.of(11, 0)));
        trip.addAttraction(new Statue("Eiffel Tower", statueTimeTable));
        Map<LocalDate, TimeInterval> churchTimetable = new HashMap<>();
        churchTimetable.put(LocalDate.of(2024, 3, 15), new TimeInterval(LocalTime.of(12, 0), LocalTime.of(15, 0)));
        trip.addAttraction(new Church("Notre Dame", churchTimetable));
        Map<LocalDate, TimeInterval> concertTimeTable = new HashMap<>();
        concertTimeTable.put(LocalDate.of(2024, 3, 15), new TimeInterval(LocalTime.of(20, 0), LocalTime.of(23, 0)));
        trip.addAttraction(new Concert("Kanye West Concert", 100.0, concertTimeTable));

        Collections.sort(trip.getAttractions());
        System.out.println("Trip to " + trip.getCityName() + " from " + trip.getPeriodStart() + " to " + trip.getPeriodEnd() + ":");
        System.out.println("Attractions:");
        for (Attraction attraction : trip.getAttractions()) {
            System.out.println(attraction.getName());
            if (attraction instanceof Visitable) {
                System.out.println("Opening hour: " + ((Visitable) attraction).getOpeningHour(LocalDate.of(2024, 3, 15)));
                System.out.println("Finishing hour: " + ((Visitable) attraction).getFinishingHour(LocalDate.of(2024, 3, 15)));
            }
            if (attraction instanceof Payable) {
                System.out.println("Entry fee: " + ((Payable) attraction).getTicketPrice() + "$");
            }
        }
    }
}
