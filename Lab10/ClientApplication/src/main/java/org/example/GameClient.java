package org.example;

import java.io.*;
import java.net.*;

public class GameClient {
    private final Socket socket;
    private final BufferedReader userInput;
    private final PrintWriter out;
    private final BufferedReader in;

    public GameClient(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        userInput = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Connected to server at " + serverAddress + ":" + serverPort);
    }

    public void communicate() {
        try {
            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine);
                if (userInputLine.equals("exit")) {
                    break;
                }
                String response = in.readLine();
                System.out.println("Server response: " + response);
                if (response.equals("Server stopped")) {
                    System.out.println("Server has stopped. Closing client.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws IOException {
        out.close();
        in.close();
        userInput.close();
        socket.close();
    }
}
