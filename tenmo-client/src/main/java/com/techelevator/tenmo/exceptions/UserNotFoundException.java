package com.techelevator.tenmo.exceptions;

/**
 * This is the exception handler class unfounded users. Returns an error when the user
 * chooses to transfer funds to a user that does not exist.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class UserNotFoundException extends Exception {

    /**
     * This method returns an error indicating that the entered user ID does not
     * belong to an existing user.
     */

    public UserNotFoundException() {
        super("User is not found. ");
    }
}
