package org.example;

public class Author extends Model{
    private final String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
