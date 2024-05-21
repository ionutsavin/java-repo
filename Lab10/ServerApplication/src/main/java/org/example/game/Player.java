package org.example.game;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private final GameBoard gameBoard;
    private final List<Ship> ships;
    private final Socket socket;
    private static final int[] SHIP_SIZES = {3, 2};
    private long remainingTime;
    private boolean isTimerRunning;

    public Player(String name, int rows, int cols, Socket socket) {
        this.name = name;
        this.socket = socket;
        this.remainingTime = 2 * 60 * 1000;
        this.isTimerRunning = false;
        gameBoard = new GameBoard(rows, cols);
        ships = new ArrayList<>();
        placeShipsRandomly();
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public void setTimerRunning(boolean timerRunning) {
        isTimerRunning = timerRunning;
    }

    private void placeShipsRandomly() {
        Random random = new Random();
        for (int size : SHIP_SIZES) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(gameBoard.getGrid().length);
                int col = random.nextInt(gameBoard.getGrid()[0].length);
                boolean horizontal = random.nextBoolean();
                placed = placeShip(row, col, size, horizontal);
            }
        }
    }

    public boolean placeShip(int startRow, int startCol, int size, boolean horizontal) {
        if (canPlaceShip(startRow, startCol, size, horizontal)) {
            Ship ship = new Ship(size);
            for (int i = 0; i < ship.getSize(); i++) {
                int row = startRow + (horizontal ? 0 : i);
                int col = startCol + (horizontal ? i : 0);
                Cell cell = gameBoard.getCell(row, col);
                ship.addCell(cell);
            }
            ships.add(ship);
            return true;
        }
        return false;
    }

    private boolean canPlaceShip(int startRow, int startCol, int size, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            int row = startRow + (horizontal ? 0 : i);
            int col = startCol + (horizontal ? i : 0);
            if (row >= gameBoard.getGrid().length || col >= gameBoard.getGrid()[0].length || gameBoard.getCell(row, col).isOccupied()) {
                return false;
            }
        }
        return true;
    }

    public List<int[]> getShipCoordinates() {
        List<int[]> allCoordinates = new ArrayList<>();
        for (Ship ship : ships) {
            allCoordinates.addAll(gameBoard.getShipCoordinates(ship));
        }
        return allCoordinates;
    }
}
