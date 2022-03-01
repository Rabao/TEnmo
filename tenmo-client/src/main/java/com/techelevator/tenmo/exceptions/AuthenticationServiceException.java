package com.techelevator.tenmo.exceptions;

/**
 * This is the exception handler class for Authentication failures.
 *
 * @author Techelevator
 *
 */

public class AuthenticationServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public AuthenticationServiceException(String message) {
        super(message);
    }
}
