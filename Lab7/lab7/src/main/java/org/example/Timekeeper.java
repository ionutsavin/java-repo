package org.example;

import java.util.concurrent.TimeUnit;

public class Timekeeper extends Thread {
    private final int timeLimitSeconds;
    private final Game game;

    public Timekeeper(int timeLimitSeconds, Game game) {
        this.timeLimitSeconds = timeLimitSeconds;
        this.game = game;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        long endTime = startTime + TimeUnit.SECONDS.toNanos(timeLimitSeconds);
        long currentTime;
        while ((currentTime = System.nanoTime()) < endTime) {
            long remainingTimeSeconds = TimeUnit.NANOSECONDS.toSeconds(endTime - currentTime) + 1;
            System.out.println("Remaining time: " + remainingTimeSeconds + " seconds");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        System.out.println("Time's up! Ending the game.");
        game.stopAllPlayers();
    }
}
