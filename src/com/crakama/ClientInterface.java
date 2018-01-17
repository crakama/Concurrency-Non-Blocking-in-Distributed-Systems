package com.crakama;

import java.io.IOException;
import java.net.Socket;

public interface ClientInterface {
    void processResponse(String cmd, Socket clientHangSock) throws IOException, ClassNotFoundException;

}
