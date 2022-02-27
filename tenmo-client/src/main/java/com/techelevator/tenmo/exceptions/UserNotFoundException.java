package com.techelevator.tenmo.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User is not found. ");
    }
}
