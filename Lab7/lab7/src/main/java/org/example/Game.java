package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag;
    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private boolean running = true;

    public Game(int size) {
        bag = new Bag(size);
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        for (Player player : players) {
            Thread playerThread = new Thread(player);
            playerThread.start();
        }
    }

    public synchronized boolean isMyTurn(Player player) {
        return players.indexOf(player) == currentPlayerIndex;
    }

    public synchronized void nextTurn() {
        if (bag.getTiles().isEmpty()) {
            stopAllPlayers();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public synchronized void stopAllPlayers() {
        running = false;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public Bag getBag() {
        return bag;
    }
}
