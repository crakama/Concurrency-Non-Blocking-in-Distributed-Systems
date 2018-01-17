package com.crakama;

import java.io.IOException;
import java.net.Socket;

public class ClientHangMan {
    Socket clientHangSock;
    ConnectionHandler connectionHandler;
    public static void main(String[] args) {
	// write your code here

        int DEFAULT_PORT = 2123;
        String DEFAULT_HOST = "localhost";
        new ClientHangMan().initClientConnection(DEFAULT_HOST,DEFAULT_PORT);
    }

    //Initialize client socket connection
    public void initClientConnection(String host, int port){
        //Create and connect to socket on a port
        try {
            clientHangSock = new Socket(host,port);
            //connectionHandler = new ConnectionHandler(clientHangSock);
            CommHandlerThread commHandlerThread = new CommHandlerThread(clientHangSock);
            Thread clientHandlerThread = new Thread(commHandlerThread);
            System.out.println("Staring client application....");
            clientHandlerThread.start();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not connect to server");
        }
    }
}
