package com.techelevator.tenmo.exceptions;

/**
 * This is the exception handler class for Invalid transfer choices. Returns an error when the user
 * chooses to view Transfer details for a Transfer that does not exist.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class InvalidTransferIdChoice extends Exception {

    /**
     * This method returns an error indicating that an incorrect transfer ID was chosen.
     */

    public InvalidTransferIdChoice() {
        super("Invalid Transfer Id. ");
    }
}