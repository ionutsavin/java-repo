package org.example;

public abstract class Person {
    private final String name;
    private final Integer age;
    private final String destination;

    public Person(String name, Integer age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    public Integer getAge() {
        return age;
    }

    public String getDestination() {
        return destination;
    }

    public String getName() {
        return name;
    }
}
