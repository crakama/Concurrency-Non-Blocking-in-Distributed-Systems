package com.crakama.Server_ThreadPoolBlocking;

import com.crakama.Server_ThreadedBlocking.net.ClientCommHandler;
import com.crakama.Server_ThreadedBlocking.net.RequestHandler;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolBlockingServer {

    private  int DEFAULT_PORT = 2123;
    private ClientCommHandler commHandler;
    /**
     * Allows a fixed number of client sockets to connect, each client request is delivered to a
     * thread pool that handles server operations
     * If a test of x clients are connected, and x exceeds number of threads in a thread pool,
     * new clients will connect to server but their requests placed in a queue thus being blocked for sometime
     * until the threads are released from (x) clients previously connected .
     */
    public static void main(String[] args){ new ThreadPoolBlockingServer().processRequests(); }

    public void processRequests(){
        try {
            ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
            ExecutorService pool = Executors.newFixedThreadPool(100);
            int count = 0;
            while (true){
                Socket clientSocket = serverSocket.accept();
                count +=1;
                System.out.println("ACCEPT" + count);
                commHandler = new ClientCommHandler(clientSocket);
                RequestHandler requestHandler = new RequestHandler(commHandler,clientSocket);
                pool.submit(requestHandler);
            }
        }catch (IOException serverconnectionfailed){
            throw new UncheckedIOException(serverconnectionfailed);
        }
    }
}

