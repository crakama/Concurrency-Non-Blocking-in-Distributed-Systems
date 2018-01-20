package com.crakama.BlockingServer;

import com.crakama.Client.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    static ConnectionHandler connectionHandler;

    /**
     * Blocks until client sends connection request
     * @clientSocket is never null because the server is always listening
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(2123);
        while (true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server accepted Connection");
            readData(clientSocket);

        }

    }

    private static void readData(Socket clientSocket) throws IOException, ClassNotFoundException {
        //connectionHandler = new ConnectionHandler(clientSocket);
        while (clientSocket.isConnected()){
           // String inData = connectionHandler.readMessage();
            //String outData = processData(inData);
            //connectionHandler.sendMessage(outData);

        }
        System.out.println("Conn problem");
    }

    private static String processData(String data){
        return "Welcome";
    }
}
