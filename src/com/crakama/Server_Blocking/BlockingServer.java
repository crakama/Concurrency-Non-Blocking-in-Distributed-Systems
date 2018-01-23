package com.crakama.Server_Blocking;

import com.crakama.common.MsgProtocol;
import com.crakama.common.MsgType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingServer {
    static ObjectOutputStream toClient;

    /**
     * Blocks until client sends connection request
     * One client is served at a time
     * @clientSocket is never null because the server is always listening
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(2123);
        while (true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server_Blocking accepted Connection");
            readData(clientSocket);
        }
    }

    private static void readData(Socket clientSocket) throws IOException, ClassNotFoundException {
        toClient = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream fromClient = new ObjectInputStream(clientSocket.getInputStream());
        while (clientSocket.isConnected()){
            try{
                MsgProtocol msg = (MsgProtocol) fromClient.readObject();
                switch (msg.getMsgType()){
                    case START:
                        processData();
                        sendResponse();

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
        System.out.println("Conn problem");
    }

    public static void sendResponse() throws IOException {
        MsgProtocol msgProtocol = new MsgProtocol(MsgType.RESPONSE,"Connected to server!!!");
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
