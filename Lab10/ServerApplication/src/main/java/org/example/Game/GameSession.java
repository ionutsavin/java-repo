package org.example.Game;

import org.example.GameServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameSession {
    private final Player[] players;
    private boolean gameStarted;
    private int currentPlayerIndex;
    private final ScheduledExecutorService scheduler;

    public GameSession(Player player1, Player player2) {
        players = new Player[]{player1, player2};
        gameStarted = false;
        currentPlayerIndex = 0;
        scheduler = Executors.newScheduledThreadPool(1);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
        if (gameStarted) {
            startTimer(players[currentPlayerIndex]);
        }
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void switchTurns() {
        stopTimer(players[currentPlayerIndex]);
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        startTimer(players[currentPlayerIndex]);
    }

    private void startTimer(Player player) {
        player.setTimerRunning(true);
        scheduler.scheduleAtFixedRate(() -> {
            if (player.isTimerRunning()) {
                player.setRemainingTime(player.getRemainingTime() - 1000);
                if (player.getRemainingTime() <= 0) {
                    stopTimer(player);
                    announceTimeoutLoss(player);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void stopTimer(Player player) {
        player.setTimerRunning(false);
    }

    private void announceTimeoutLoss(Player player) {
        Player opponent = players[(currentPlayerIndex + 1) % 2];
        try {
            PrintWriter out = new PrintWriter(player.getSocket().getOutputStream(), true);
            PrintWriter opponentOut = new PrintWriter(opponent.getSocket().getOutputStream(), true);
            out.println("Time out! You lost the game.");
            opponentOut.println("Opponent ran out of time! You won the game.");
            GameServer.removeGameSession(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkWinCondition() {
        for (Player player : players) {
            boolean allShipsSunk = player.getShips().stream().allMatch(ship ->
                    ship.getCells().stream().allMatch(Cell::isHit)
            );
            if (allShipsSunk) {
                return true;
            }
        }
        return false;
    }
}
