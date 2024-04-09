package org.example;

import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private final int rows, cols;
    private final int boardWidth, boardHeight;
    private final int cellWidth, cellHeight;
    private final List<Line2D> gridLines;
    private final List<Stone> stones;
    private final List<Stick> sticks;
    private final boolean[][] coloredOvals;
    private final boolean isPlayer1Turn;
    private final int prevSelectedX;
    private final int prevSelectedY;

    public GameState(int rows, int cols, int boardWidth, int boardHeight, int cellWidth, int cellHeight,
                     List<Line2D> gridLines, List<Stone> stones, List<Stick> sticks, boolean[][] coloredOvals,
                     boolean isPlayer1Turn, int prevSelectedX, int prevSelectedY) {
        this.rows = rows;
        this.cols = cols;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.gridLines = new ArrayList<>(gridLines);
        this.stones = new ArrayList<>(stones);
        this.sticks = new ArrayList<>(sticks);
        this.coloredOvals = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(coloredOvals[i], 0, this.coloredOvals[i], 0, cols);
        }
        this.isPlayer1Turn = isPlayer1Turn;
        this.prevSelectedX = prevSelectedX;
        this.prevSelectedY = prevSelectedY;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public List<Line2D> getGridLines() {
        return gridLines;
    }

    public List<Stone> getStones() {
        return stones;
    }

    public List<Stick> getSticks() {
        return sticks;
    }

    public boolean[][] getColoredOvals() {
        return coloredOvals;
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public int getPrevSelectedX() {
        return prevSelectedX;
    }

    public int getPrevSelectedY() {
        return prevSelectedY;
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
        }
    }

    public static GameState loadFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (GameState) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game state: " + e.getMessage());
            return null;
        }
    }
}
