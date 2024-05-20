package org.example.Game;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final Cell[][] grid;

    public GameBoard(int rows, int cols) {
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return null;
        }
        return grid[row][col];
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public List<int[]> getShipCoordinates(Ship ship) {
        List<int[]> coordinates = new ArrayList<>();
        for (Cell cell : ship.getCells()) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == cell) {
                        coordinates.add(new int[]{i, j});
                    }
                }
            }
        }
        return coordinates;
    }
}
