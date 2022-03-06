package com.techelevator.tenmo.exceptions;

/**
 * This exception is thrown when insufficient or invalid
 * funds are entered.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */
public class BadFunds extends Exception {
    public BadFunds(){
        super("Bad funds!");
    }
}
