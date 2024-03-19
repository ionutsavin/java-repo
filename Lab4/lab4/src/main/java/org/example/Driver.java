package org.example;

public class Driver extends Person implements Comparable<Driver> {
    public Driver(String name, int age, String destination) {
        super(name, age, destination);
    }

    @Override
    public String toString() {
        return "Driver: " + this.getName();
    }

    @Override
    public int compareTo(Driver o) {
        return this.getAge().compareTo(o.getAge());
    }
}
