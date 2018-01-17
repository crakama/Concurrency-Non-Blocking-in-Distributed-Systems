package com.crakama;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommHandlerThread implements Runnable {
    Socket clientHangSock;
    ClientInterface clientInterface;
    UserInputHandler userInputHandler;


    public CommHandlerThread(Socket clientHangSock) throws IOException {
        this.clientHangSock = clientHangSock;
        this.userInputHandler= new UserInputHandler();
        this.clientInterface = new ClientImpl(clientHangSock);
    }

    /**
     * While loop used to allow playing of multiple games.
     */
    //Pick user command from CMD e.g start
    //Open output stream
    //Write user command to output stream
    //Wait for server reply, read via a socket input stream
    //Server accepts connection, picks a word from dict or txt and sends it back filled with dashes to the user
    //Client should have a way of always waiting for user input that is not command - while loop.
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Enter command to start the game: e.g start or quit to exit");
                String usercommands =  userInputHandler.pickUserCommand();
              switch (usercommands){
                  case "start":

                      clientInterface.processResponse(usercommands,clientHangSock);
                      break;
                  case "quit":
                      //System.exit();
                      break;
                  default:
                      //Read user input and send the character or word to sever for processing
              }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Could not pick user command");
            }

        }
    }//Run



}
