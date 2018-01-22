package com.crakama.Client.net;

public interface OutputHandler {
    /**
     * Observer pattern, Interface in which the server uses to return response and client uses to process resp.
     * Eliminates upwards dependency on client side
     * @param msg
     */
    void handleServerResponse(String msg);

    void handleErrorResponse(String connectionFailure);


    void informUser();
}
