package org.example;

public class Main {
    public static void main(String[] args) {
        int port = 12345;
        GameServer gameServer = new GameServer(port);
        gameServer.start();
    }
}