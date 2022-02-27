package com.techelevator.tenmo.exceptions;

public class InvalidUserChoiceException extends Exception {
    public InvalidUserChoiceException() {
        super("Silly! You cannot send money to yourself. ");
    }
}