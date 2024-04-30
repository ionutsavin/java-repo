package org.example;

public class Genre extends Model{
    private final String name;

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
