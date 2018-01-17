package com.crakama;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    /**
     * Blocks until client sends connection request
     * @clientSocket is never null because the server is always listening
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(2123);
        while (true){
            Socket clientSocket = serverSocket.accept();
            ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket);
            readData(connectionHandler);
        }
    }

    private static void readData(ConnectionHandler connectionHandler) throws IOException, ClassNotFoundException {
        String inData = connectionHandler.readMessage();
        String outData = processData(inData);
        connectionHandler.sendMessage(outData);
    }

    private static String processData(String data){
        return "Welcome";
    }
}
