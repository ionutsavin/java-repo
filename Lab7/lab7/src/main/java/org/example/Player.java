package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private Game game;
    private final List<Tile> tiles = new ArrayList<>();
    private final Object lock = new Object();

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

    public void run() {
        while (game != null && game.isRunning()) {
            synchronized (lock) {
                if (game != null && game.isMyTurn(this)) {
                    List<Tile> extractedTiles = game.getBag().extractTiles(1);
                    if (!extractedTiles.isEmpty()) {
                        tiles.add(extractedTiles.get(0));
                        System.out.println(name + " picked tile: " + extractedTiles.get(0));
                        game.nextTurn();
                    }
                }
            }
        }
    }
}
