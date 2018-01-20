package com.crakama.Client;

public interface OutputHandler {
    void handleServerResponse(String msg);

    void handleErrorResponse(Throwable connectionFailure);
}
