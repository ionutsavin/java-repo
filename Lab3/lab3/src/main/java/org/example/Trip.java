package org.example;
import java.time.*;
import java.util.*;
public class Trip {
    private final String cityName;
    private final LocalTime periodStart;
    private final LocalTime periodEnd;
    private final List<Attraction> attractions;

    public Trip(String cityName, LocalTime periodStart, LocalTime periodEnd) {
        this.cityName = cityName;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
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

    public LocalTime getPeriodStart() {
        return periodStart;
    }

    public LocalTime getPeriodEnd() {
        return periodEnd;
    }
}
