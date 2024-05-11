package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;
        try {
            GameClient gameClient = new GameClient(serverAddress, serverPort);
            gameClient.communicate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}