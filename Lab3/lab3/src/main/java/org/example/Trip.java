package org.example;

import java.time.LocalDate;
import java.util.*;

public class Trip {
    private final String cityName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<Attraction> attractions;

    public Trip(String cityName, LocalDate periodStart, LocalDate periodEnd) {
        this.cityName = cityName;
        this.startDate = periodStart;
        this.endDate = periodEnd;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public String getCityName() {
        return cityName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void visitableLocationsButNotPayable(LocalDate date) {
        List<Attraction> attractionsNotPayable = new ArrayList<>();
        for (Attraction a : attractions) {
            if (a instanceof Visitable && !(a instanceof Payable)) {
                attractionsNotPayable.add(a);
            }
        }

        attractionsNotPayable.sort(Comparator.comparing(attraction ->
                        ((Visitable) attraction).getOpeningHour(date),
                Comparator.nullsLast(Comparator.naturalOrder())));

        System.out.println("Visitable locations but not payable on " + date + ": ");
        for (Attraction attraction : attractionsNotPayable) {
            System.out.println("Attraction: " + attraction.getName());
            System.out.println("Opening hour: " +
                    ((Visitable) attraction).getOpeningHour(date));
        }
        System.out.println();
    }
}
