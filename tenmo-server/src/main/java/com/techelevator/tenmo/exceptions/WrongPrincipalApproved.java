package com.techelevator.tenmo.exceptions;

/**
 * This exception is thrown when the wrong Principal
 * tries to approve a pending transfer request.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */
public class WrongPrincipalApproved extends Exception {
    public WrongPrincipalApproved() {
        super("Silly! You cannot approve another another uses transfers! ");
    }
}
