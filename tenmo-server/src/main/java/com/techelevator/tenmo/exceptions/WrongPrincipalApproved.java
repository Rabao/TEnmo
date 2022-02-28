package com.techelevator.tenmo.exceptions;

public class WrongPrincipalApproved extends Exception {
    public WrongPrincipalApproved() {
        super("Silly! You cannot approve another another uses transfers! ");
    }
}
