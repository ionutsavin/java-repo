package org.example;


public class Main {
    public static void main(String[] args) {
        Game game = new Game(5);
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }
}
