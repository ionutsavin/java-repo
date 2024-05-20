package org.example;

import org.example.Game.Cell;
import org.example.Game.GameSession;
import org.example.Game.Player;

import java.io.*;
import java.net.*;
import java.util.List;

public class ClientThread extends Thread {
    private final Socket clientSocket;
    private final GameServer server;
    private Player currentPlayer;

    public ClientThread(Socket clientSocket, GameServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
        System.out.println("New client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Client request: " + request);

                if (request.startsWith("create game")) {
                    handleCreateGame(out, request);
                } else if (request.startsWith("join game")) {
                    handleJoinGame(out, request);
                } else if (request.startsWith("submit move")) {
                    handleSubmitMove(out, request);
                } else if (request.equals("stop")) {
                    out.println("Server stopped");
                    clientSocket.close();
                    server.stop();
                    break;
                } else {
                    out.println("Server received the request: " + request);
                }
            }

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCreateGame(PrintWriter out, String request) {
        String[] parts = request.split(" ");
        if (parts.length < 3) {
            out.println("Invalid command. Usage: create game <gameId>");
            return;
        }

        String gameId = parts[2];
        if (GameServer.getGameSessions().containsKey(gameId)) {
            out.println("Game with this ID already exists. Choose a different ID.");
            return;
        }

        currentPlayer = new Player("Player1", 10, 10, clientSocket);
        GameSession gameSession = new GameSession(currentPlayer, null);
        GameServer.addGameSession(gameId, gameSession);
        out.print("Game created with ID: " + gameId + " ");
        sendShipCoordinates(out, currentPlayer);
    }

    private void handleJoinGame(PrintWriter out, String request) {
        String[] parts = request.split(" ");
        if (parts.length < 3) {
            out.println("Invalid command. Usage: join game <gameId>");
            return;
        }

        String gameId = parts[2];
        GameSession gameSession = GameServer.getGameSessions().get(gameId);
        if (gameSession == null) {
            out.println("No game with this ID exists.");
            return;
        }

        if (gameSession.getPlayers()[1] != null) {
            out.println("Game already has two players.");
            return;
        }

        currentPlayer = new Player("Player2", 10, 10, clientSocket);
        gameSession.getPlayers()[1] = currentPlayer;
        gameSession.setGameStarted(true);
        out.print("Joined game with ID: " + gameId + " ");
        sendShipCoordinates(out, currentPlayer);
    }

    private void handleSubmitMove(PrintWriter out, String request) {
        String[] parts = request.split(" ");
        if (parts.length < 5) {
            out.println("Invalid command. Usage: submit move <gameId> <row> <col>");
            return;
        }

        String gameId = parts[2];
        int row = Integer.parseInt(parts[3]);
        int col = Integer.parseInt(parts[4]);
        GameSession gameSession = GameServer.getGameSessions().get(gameId);
        if (gameSession == null) {
            out.println("No game with this ID exists.");
            return;
        }

        if(!gameSession.isGameStarted()) {
            out.println("The game has not started.");
            return;
        }

        Player player1 = gameSession.getPlayers()[0];
        Player player2 = gameSession.getPlayers()[1];

        if (player1 == null || player2 == null) {
            out.println("Both players need to join before making moves.");
            return;
        }

        int currentPlayerIndex = gameSession.getCurrentPlayerIndex();
        if (currentPlayer != gameSession.getPlayers()[currentPlayerIndex]) {
            out.println("It's not your turn!");
            return;
        }

        Player opponent = gameSession.getPlayers()[(currentPlayerIndex + 1) % 2];
        Cell targetCell = opponent.getGameBoard().getCell(row, col);

        if(targetCell == null){
            out.println("Invalid coordinates. Choose a cell within the board.");
            return;
        }

        if (targetCell.isHit()) {
            out.println("This cell has already been targeted. Choose another cell.");
            return;
        }

        targetCell.setHit(true);
        if (targetCell.isOccupied()) {
            if(gameSession.checkWinCondition()){
                announceWinner(gameSession, currentPlayer);
                GameServer.removeGameSession(gameSession);
            } else {
                out.println("Hit!");
            }
        } else {
            out.println("Miss!");
        }

        gameSession.switchTurns();
    }

    private void announceWinner(GameSession gameSession, Player currentPlayer) {
        Player[] players = gameSession.getPlayers();
        Player winner = players[0] == currentPlayer ? players[0] : players[1];
        StringBuilder message = new StringBuilder(winner.getName() + " won the game!");
        for (Player player : players) {
            PrintWriter out = null;
            try {
                out = new PrintWriter(player.getSocket().getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            out.println(message);
        }
    }

    private void sendShipCoordinates(PrintWriter out, Player player) {
        List<int[]> shipCoordinates = player.getShipCoordinates();
        StringBuilder message = new StringBuilder("Your ship coordinates are: ");
        for (int[] coordinate : shipCoordinates) {
            message.append("Ship at (").append(coordinate[0]).append(", ").append(coordinate[1]).append(") ");
        }
        out.println(message);
    }
}
