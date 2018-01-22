package com.crakama.Server_ThreadedBlocking;

import com.crakama.common.MsgProtocol;
import com.crakama.common.MsgType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedBlockingServer {
    static ObjectOutputStream toClient;
    private  int DEFAULT_PORT = 2123;
    /**
     * Allows many client sockets to connect because server operations
     * Are handled by a different thread
     * @clientSocket is never null because the server is always listening
     */
    public static void main(String[] args){

        new ThreadedBlockingServer().incomingRequests();

    }

    public void incomingRequests(){
        try {
            ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);

            while (true){
                Socket clientSocket = serverSocket.accept();
                toClient = new ObjectOutputStream(clientSocket.getOutputStream());
                sendResponse("Server Accepted Connection!!!.");
                new Thread(() -> {
                        try {
                        readData(clientSocket);
                        } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                        }
                        }).start();

            }
        }catch (IOException serverconnectionfailed){
            throw new UncheckedIOException(serverconnectionfailed);
        }
    }

    public void readData(Socket clientSocket) throws IOException, ClassNotFoundException {

        ObjectInputStream fromClient = new ObjectInputStream(clientSocket.getInputStream());
        while (clientSocket.isConnected()){
            try{
                MsgProtocol msg = (MsgProtocol) fromClient.readObject();
                switch (msg.getMsgType()){
                    case START:
                        String response = processData();
                        sendResponse(response);
                        break;
                    case GUESS:
                        processGuess();
                        break;
                    case STOP:
                        processData();
                }
            }catch (IOException e){

            }
        }
        sendResponse("Conn problem");
    }

    public static void sendResponse(String response) throws IOException {
        MsgProtocol msgProtocol = new MsgProtocol(MsgType.RESPONSE,response);
        toClient.writeObject(msgProtocol);
        toClient.flush();
        toClient.reset();
    }
    private static String processData(){
        return "Welcome, Game started";
    }
    private static String processGuess(){
        return "Guess received";
    }
}

