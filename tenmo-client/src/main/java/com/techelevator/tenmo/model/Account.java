package com.techelevator.tenmo.model;

/**
 * This model class contains details necessary for interfacing with the database's Account table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class Account {

    /**
     * The property used to contain the account ID.
     */
    private int account_id;

    /**
     * The property used to contain the user ID.
     */
    private int user_id;

    /**
     * The property used to contain the account balance.
     */
    private double balance;


    /**
     * This method returns the account ID when invoked.
     *
     * @return the account ID stored within the object's account_id property.
     *
     */
    public int getAccount_id() { return account_id; }

    /**
     * This method retrieves and stores the account ID when invoked.
     *
     * @Param account_id Sets the object's account_id property.
     *
     */
    public void setAccount_id(int account_id) { this.account_id = account_id; }

    /**
     * This method returns the user ID when invoked.
     *
     * @return the user ID stored within the object's user_id property.
     *
     */
    public int getUser_id() { return user_id; }

    /**
     * This method retrieves and stores the user ID when invoked.
     *
     * @Param user_id Sets the object's user_id property.
     *
     */
    public void setUser_id(int user_id) { this.user_id = user_id; }

    /**
     * This method returns the account balance when invoked.
     *
     * @return the balance stored within the object's balance property.
     *
     */
    public double getBalance() { return balance; }

    /**
     * This method retrieves and stores the account balance when invoked.
     *
     * @Param balance Sets the object's balance property.
     *
     */
    public void setBalance(double balance) { this.balance = balance; }
}
