package com.crakama.Client;

import java.io.IOException;
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
                      System.exit(0);
                      break;
                  default:
              }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Could not pick user command");
            }

        }
    }

}
