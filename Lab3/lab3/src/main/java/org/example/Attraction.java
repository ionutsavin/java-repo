package org.example;

public abstract class Attraction implements Comparable<Attraction> {
    private final String name;

    public Attraction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Attraction other) {
        return this.name.compareTo(other.name);
    }
}
