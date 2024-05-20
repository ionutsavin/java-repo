package org.example;

import org.example.Game.GameSession;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;


public class GameServer {
    private ServerSocket serverSocket;
    private final int port;
    private static final Map<String, GameSession> gameSessions = new HashMap<>();


    public GameServer(int port) {
        this.port = port;
    }

    public static void addGameSession(String gameId, GameSession gameSession) {
        gameSessions.put(gameId, gameSession);
    }

    public static void removeGameSession(GameSession gameSession) {
        gameSessions.entrySet().removeIf(entry -> entry.getValue().equals(gameSession));
    }

    public static Map<String, GameSession> getGameSessions() {
        return gameSessions;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
            System.out.println("Server stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
