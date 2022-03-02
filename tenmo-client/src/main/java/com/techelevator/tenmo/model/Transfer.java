package com.techelevator.tenmo.model;

/**
 * This model class contains details necessary for storing data
 * retrieved from the database Transfers table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class Transfer {

    /**
     * The property used to contain the transfer record's ID.
     */
    private int id;

    /**
     * The property used to contain the transfer record's type ID.
     */
    private int transferTypeId;

    /**
     * The property used to contain the transfer record's status ID
     * (whether the transfer is Pending or Approved).
     */
    private int transferStatusId;

    /**
     * The property used to contain the transfer's sender.
     */
    private int accountFrom;

    /**
     * The property used to contain the transfer's recipient.
     */
    private int accountTo;

    /**
     * The property used to contain the amount transferred.
     */
    private double amount;

    /**
     * This method returns the transfer record's ID.
     *
     * @return the transfer record's ID.
     *
     */
    public int getId() { return id; }

    /**
     * This method retrieves and stores the transfer record's ID.
     *
     * @param id Stores the passed in transfer record's ID.
     *
     */
    public void setId(int id) { this.id = id; }

    /**
     * This method returns the transfer record's type ID.
     *
     * @return the transfer record's type ID.
     *
     */
    public int getTransferTypeId() { return transferTypeId; }

    /**
     * This method retrieves and stores the transfer record's type ID.
     *
     * @param transferTypeId Stores the passed in transfer record's type ID.
     *
     */
    public void setTransferTypeId(int transferTypeId) { this.transferTypeId = transferTypeId; }

    /**
     * This method returns the transfer record's status ID.
     *
     * @return the transfer record's status ID.
     *
     */
    public int getTransferStatusId() { return transferStatusId; }

    /**
     * This method retrieves and stores the transfer record's status ID.
     *
     * @param transferStatusId Stores the passed in transfer record's status ID.
     *
     */
    public void setTransferStatusId(int transferStatusId) { this.transferStatusId = transferStatusId; }

    /**
     * This method returns the transfer record's sender.
     *
     * @return the transfer record's sender account.
     *
     */
    public int getAccountFrom() { return accountFrom; }

    /**
     * This method retrieves and stores the transfer record's sender.
     *
     * @param accountFrom Stores the passed in transfer record's sending account.
     *
     */
    public void setAccountFrom(int accountFrom) { this.accountFrom = accountFrom; }

    /**
     * This method returns the transfer record's recipient.
     *
     * @return the transfer record's receiving account.
     *
     */
    public int getAccountTo() { return accountTo; }

    /**
     * This method retrieves and stores the transfer record's recipient.
     *
     * @param accountTo Stores the passed in transfer record's receiving account.
     *
     */
    public void setAccountTo(int accountTo) { this.accountTo = accountTo; }

    /**
     * This method returns the recorded transfer amount.
     *
     * @return the record of the transferred amount.
     *
     */
    public double getAmount() { return amount; }

    /**
     * This method retrieves and stores the amount that was transferred.
     *
     * @param amount Stores the passed in transfer record's transfer amount.
     *
     */
    public void setAmount(double amount) { this.amount = amount; }
}
