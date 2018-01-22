package com.crakama.Client.net;

import com.crakama.common.MsgProtocol;
import com.crakama.common.MsgType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerCommHandler {
    private ObjectOutputStream writeData;
    private ObjectInputStream readData;
    Socket clientSocket;
    public ServerCommHandler(){

    }

    public void connect(String host, int port, OutputHandler outputHandler) throws IOException {
        this.clientSocket = new Socket(host,port);
        readData = new ObjectInputStream(clientSocket.getInputStream());
        writeData = new ObjectOutputStream(clientSocket.getOutputStream());
        new Thread(new ListenerThread(outputHandler)).start();

    }

    public void startGame() throws IOException {
        sendMsg(MsgType.START,null);
    }
    public void sendGuess(String guess) throws IOException {
        sendMsg(MsgType.GUESS,guess);
    }

    public void sendMsg(MsgType type, String body) throws IOException {
        MsgProtocol msg = new MsgProtocol(type,body);
        writeData.writeObject(msg);
        writeData.flush();
        writeData.reset();
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
                    MsgProtocol msg = (MsgProtocol)readData.readObject();
                    outputHandler.handleServerResponse(getMgsBody(msg));
                }
            } catch (ClassNotFoundException|IOException e) {
                outputHandler.handleErrorResponse("IO or Class Not Found Error!!\n"+ e);

            } catch (Throwable connectionFailure){
                outputHandler.handleErrorResponse("Connection Lost !!\n" + connectionFailure);
            }
        }

    }

    private String getMgsBody(MsgProtocol fromServer){
        return fromServer.getMsgBody();
    }
}
