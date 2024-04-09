package org.example;

public class Tile {
    private final int number1;
    private final int number2;

    public Tile(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public String toString() {
        return "(" + number1 + "," + number2 + ")";
    }
}
