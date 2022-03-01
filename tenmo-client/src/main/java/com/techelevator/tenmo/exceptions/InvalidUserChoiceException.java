package com.techelevator.tenmo.exceptions;

/**
 * This is the exception handler class for Principal user selection. Returns an error when the user
 * chooses to Transfer funds to their own account.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */


public class InvalidUserChoiceException extends Exception {

    /**
     * This method returns an error indicating that the user has chosen themselves as a transfer target.
     */

    public InvalidUserChoiceException() {
        super("Silly! You cannot send money to yourself. ");
    }
}