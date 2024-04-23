package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag;
    private final int timeLimit;
    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private boolean running = true;
    private boolean isAWinner = false;

    public Game(int size, int timeLimit) {
        bag = new Bag(size);
        this.timeLimit = timeLimit;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        Timekeeper timekeeper = new Timekeeper(timeLimit, this);
        timekeeper.start();
        for (Player player : players) {
            Thread playerThread = new Thread(player);
            playerThread.start();
        }
        while (running) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                determineWinnerInGame();
            }
        }
        if (!isAWinner) {
            determineWinner();
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
            notify();
        }
    }

    public synchronized void stopAllPlayers() {
        running = false;
        notifyAll();
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized Bag getBag() {
        return bag;
    }

    private synchronized void determineWinnerInGame() {
        Player winner = null;
        for (Player player : players) {
            if (player.isMaximumSequence()) {
                if (player.getMaximumSequenceLength() == bag.getN()) {
                    winner = player;
                    break;
                }
            }
        }

        if (winner != null) {
            System.out.println("The winner is: " + winner.getName() + " with a sequence of length " + winner.getTiles().size() + "!");
            /*System.out.println("Tiles of the winner:");
            for (Tile tile : winner.getTiles()) {
                System.out.println(tile.getNumber1() + ", " + tile.getNumber2());
            }*/
            running = false;
            isAWinner = true;
        }

    }

    private void determineWinner() {
        Player winner = null;
        int maxPoints = Integer.MIN_VALUE;
        for (Player player : players) {
            int points = player.getMaximumSequenceLength();
            if (points > maxPoints) {
                maxPoints = points;
                winner = player;
            }
        }
        if (winner != null) {
            System.out.println("The winner is: " + winner.getName() + " with " + maxPoints + " points!");
        }
    }
}
