package org.example;

public class Passenger extends Person implements Comparable<Passenger> {
    public Passenger(String name, int age, String destination) {
        super(name, age, destination);
    }

    @Override
    public String toString() {
        return "Passenger: " + this.getName();
    }

    @Override
    public int compareTo(Passenger o) {
        return this.getName().compareTo(o.getName());
    }
}
