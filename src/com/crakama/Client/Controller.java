package com.crakama.Client;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.CompletableFuture;

public class Controller {
    private final ConnectionHandler connectionHandler = new ConnectionHandler();

    public void connect(String host, int port, OutputHandler outputHandler) {
        CompletableFuture.runAsync(()-> {
            try {
                System.out.println(host + port+ " Controller");
                connectionHandler.connect(host,port, outputHandler);
                System.out.println(host + port+ " Controller 2");
            }catch (IOException e){
                throw new UncheckedIOException(e);
            }

        });
    }
}
