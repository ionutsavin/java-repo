package org.example.Game;

public class Cell {
    private boolean occupied;
    private boolean hit;

    public Cell() {
        this.occupied = false;
        this.hit = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}