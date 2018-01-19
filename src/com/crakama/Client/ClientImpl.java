package com.crakama.Client;

import java.io.*;
import java.net.Socket;

public class ClientImpl implements ClientInterface {

    Socket cSocket;
    ConnectionHandler connectionHandler;
    UserInputHandler userInputHandler;

    public ClientImpl(Socket clientHangSock) throws IOException {
        this.cSocket = clientHangSock;
        this.connectionHandler = new ConnectionHandler(cSocket);
        System.out.println("ConnectionHandler ClientImpl");
        this.userInputHandler = new UserInputHandler();
    }

    @Override
    public void processResponse(String receivedCMD, Socket clientHangSock) throws IOException, ClassNotFoundException {
        System.out.println("Sending start request to server:::...." + receivedCMD);
        connectionHandler.sendMessage(receivedCMD);

        while (connectionHandler.isConnected()){
           String receivedMessage = connectionHandler.readMessage();

           System.out.println("//***-------------------------------------------------------------------------***\n\n"+receivedMessage+
                               "\n\n***-------------------------------------------------------------------------***\n");
           String userResponse = userInputHandler.pickUserCommand();
           System.out.println(userResponse);
           connectionHandler.sendMessage(userResponse);
            System.out.println("messade sent");
        }
        //connectionHandler.closeConnection();
    }

}

