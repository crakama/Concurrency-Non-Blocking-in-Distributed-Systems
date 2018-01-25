package com.crakama.Client.test;

import com.crakama.common.MsgProtocol;
import com.crakama.common.MsgType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class testNumOfConnectedClients {
    private static ObjectOutputStream writeData;
    private static ObjectInputStream readData;
    Socket clientSocket;
    public static void main(String[] args) throws InterruptedException {
        Socket[] sockets = new Socket[3000];

        for(int i = 0; i < sockets.length; i++){
            try {
                sockets[i] = new Socket("localhost",2123);
                readData = new ObjectInputStream(sockets[i].getInputStream());
                writeData = new ObjectOutputStream(sockets[i].getOutputStream());
                System.out.println("About to connect");
                initialiseGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(100000);
    }

    public static void initialiseGame() throws IOException {
        sendMsg(MsgType.START,null);
    }

    public static void sendMsg(MsgType type, String body) throws IOException {
        MsgProtocol msg = new MsgProtocol(type,body);
        writeData.writeObject(msg);
        writeData.flush();
        writeData.reset();
        System.out.println("Sent message");
    }
}
