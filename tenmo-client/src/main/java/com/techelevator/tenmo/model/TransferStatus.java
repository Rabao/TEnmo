package com.techelevator.tenmo.model;

/**
 * This model class contains details necessary for storing data
 * retrieved from the database Transfer Statuses table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class TransferStatus {

    /**
     * The property used to contain the transfer's status ID.
     */
    private int transferStatusId;

    /**
     * The property used to contain the transfer's status description.
     */
    private String transferStatusDesc;

    /**
     * This method returns the transfer's status ID.
     *
     * @return the transfer's status ID.
     *
     */
    public int getTransferStatusId() { return transferStatusId; }

    /**
     * This method retrieves and stores the transfer's status ID.
     *
     * @Param transferStatusId Stores the passed in transfer's status ID.
     *
     */
    public void setTransferStatusId(int transferStatusId) { this.transferStatusId = transferStatusId; }

    /**
     * This method returns the transfer's status description.
     *
     * @return the transfer's status description.
     *
     */
    public String getTransferStatusDesc() { return transferStatusDesc; }

    /**
     * This method retrieves and stores the transfer's status description.
     *
     * @Param transferStatusDesc Stores the passed in transfer's status description.
     *
     */
    public void setTransferStatusDesc(String transferStatusDesc) { this.transferStatusDesc = transferStatusDesc; }
}
