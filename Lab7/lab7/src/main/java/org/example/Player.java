package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private Game game;
    private final List<Tile> tiles = new ArrayList<>();
    private final Object lock = new Object();
    private boolean maximumSequence = false;
    private int maximumSequenceLength = 0;

    public Player(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public String getName() {
        return name;
    }

    public boolean isMaximumSequence() {
        return maximumSequence;
    }

    public int getMaximumSequenceLength() {
        return maximumSequenceLength;
    }

    public void run() {
        while (game != null && game.isRunning()) {
            synchronized (lock) {
                if (game != null && game.isMyTurn(this)) {
                    int indexToRemove = calculateIndexToRemove();
                    List<Tile> extractedTiles = game.getBag().extractTiles(indexToRemove);
                    if (!extractedTiles.isEmpty()) {
                        tiles.add(extractedTiles.get(0));
                        //System.out.println(name + " picked tile: " + extractedTiles.get(0));
                        game.nextTurn();
                    }
                }
            }
        }
    }

    private int calculateIndexToRemove() {
        int defaultIndex = defaultStrategy();
        if (defaultIndex != -1) {
            return defaultIndex;
        }

        if (tiles.size() <= game.getBag().getN() - 1 && !tiles.isEmpty() && !maximumSequence) {
            Tile firstTile = tiles.get(0);
            for (int i = tiles.size() - 1; i >= 0; i--) {
                Tile lastTile = tiles.get(i);
                for (int j = 0; j < game.getBag().getTiles().size(); j++) {
                    Tile tile = game.getBag().getTiles().get(j);
                    if (tile.getNumber1() == lastTile.getNumber2() && tile.getNumber2() == firstTile.getNumber1()) {
                        maximumSequence = true;
                        maximumSequenceLength = tiles.size() + 1;
                        //System.out.println(name + " has maximum sequence length: " + maximumSequenceLength);
                        return j;
                    }
                }
            }
        }

        return 0;
    }

    private int defaultStrategy() {
        Tile lastTile = null;
        if (!tiles.isEmpty()) {
            lastTile = tiles.get(tiles.size() - 1);
        }

        if (lastTile != null) {
            for (int i = 0; i < game.getBag().getTiles().size(); i++) {
                Tile tile = game.getBag().getTiles().get(i);
                if (tile.getNumber1() == lastTile.getNumber2() && !containsSecondNumber(tile.getNumber2())) {
                    return i;
                }
            }
        }

        return -1;
    }

    private boolean containsSecondNumber(int number) {
        for (Tile t : tiles) {
            if (t.getNumber1() == number || t.getNumber2() == number) {
                return true;
            }
        }
        return false;
    }
}
