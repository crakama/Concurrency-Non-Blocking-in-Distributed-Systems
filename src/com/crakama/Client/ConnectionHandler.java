package com.crakama.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler {
    private ObjectOutputStream writeData;
    private ObjectInputStream readData;
    private Socket clientSocket;

    public void connect(String host, int port, OutputHandler outputHandler) throws IOException {
        System.out.println(host + port+ " ConnectionHandler");
        this.clientSocket = new Socket(host,port);
        System.out.println(host + port+ " Connection Success1");
        readData = new ObjectInputStream(clientSocket.getInputStream());
        writeData = new ObjectOutputStream(clientSocket.getOutputStream());
        System.out.println(host + port+ " Connection Success2");
        new Thread(new ListenerThread(outputHandler)).start();

    }

    private class ListenerThread implements  Runnable{
        private final OutputHandler outputHandler;
        public ListenerThread(OutputHandler outputHandler){
            this.outputHandler = outputHandler;

        }
        @Override
        public void run(){
            try {
                for(;true;){
                    String msg = (String)readData.readObject();
                    outputHandler.handleServerResponse(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (Throwable connectionFailure){
                outputHandler.handleErrorResponse(connectionFailure);
            }
        }

    }
}
