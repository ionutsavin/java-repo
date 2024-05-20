package org.example.Game;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final int size;
    private final List<Cell> cells;

    public Ship(int size) {
        this.size = size;
        cells = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
        cell.setOccupied(true);
    }
}
