package com.crakama.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdInterpreter implements Runnable{
    private boolean receivingCmds = false;
    Controller controller;
    private BufferedReader bufferedReader;

    public void start(){
        receivingCmds = true;
        controller = new Controller();
        new Thread(this).start();
    }
    @Override
    public void run() {
        while (receivingCmds){
            try {
                System.out.println(" Enter command");
                CmdHandler cmdHandler = new CmdHandler(requestHandler());
                switch (cmdHandler.getCmd()){

                    case CONNECT:
                        System.out.println(cmdHandler.getCmd()+ " RUN TRY");
                        System.out.println(cmdHandler.getParameters(1)+ " RUN TRY1");
                        System.out.println(cmdHandler.getParameters(2)+ " RUN TRY2");
                        controller.connect(cmdHandler.getParameters(1),
                                Integer.parseInt(cmdHandler.getParameters(2)), new ServerResponse());

                        break;
                    case START:

                    case QUIT:
                        break;

                }

            }catch (Exception e){

            }
        }
    }

    /**
     * Handles user input from commandline
     */
    private String requestHandler() throws IOException {
            this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String readBuffer = bufferedReader.readLine();
            return readBuffer;
        }


    private class ServerResponse implements OutputHandler{
        @Override
        public void handleServerResponse(String receivedMessage) {
            System.out.println("//***-------------------------------------------------------------------------***\n\n"+receivedMessage+
                    "\n\n***-------------------------------------------------------------------------***\n");
        }

        @Override
        public void handleErrorResponse(Throwable connectionFailure) {
            System.out.println("//***-------------------------------------------------------------------------***\n\n"+connectionFailure+
                    "\n\n***-------------------------------------------------------------------------***\n");
        }
    }
}
