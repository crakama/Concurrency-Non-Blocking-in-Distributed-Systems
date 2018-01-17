package com.crakama;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles user input from commandline
 */
public class UserInputHandler {
    private BufferedReader bufferedReader;
    public UserInputHandler(){
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }
    public  String pickUserCommand() throws IOException {
        return this.bufferedReader.readLine();
    }
}
