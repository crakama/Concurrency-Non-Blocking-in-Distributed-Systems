package com.crakama;

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

    public ClientImpl() {

    }

    @Override
    public void processResponse(String receivedCMD, Socket clientHangSock) throws IOException, ClassNotFoundException {
        System.out.println("Sending start request to server:::...." + receivedCMD);
        connectionHandler.sendMessage(receivedCMD);

        while (connectionHandler.isConnected()){
           String receivedMessage = connectionHandler.readMessage();
           System.out.println("::::::::::::::::::START OF MESSAGE FROM SERVER::::::::::::::::::\n\n"+receivedMessage+
                               "\n\n::::::::::::::::::END OF MESSAGE FROM SERVER::::::::::::::::::\n");
           String userResponse = userInputHandler.pickUserCommand();
           System.out.println(userResponse);
           connectionHandler.sendMessage(userResponse);

        }

    }

}

