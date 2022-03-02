package com.techelevator.tenmo.model;

/**
 * This model class contains details necessary for storing data
 * retrieved from the database Transfer Types table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class TransferType {

    /**
     * The property used to contain the transfer's type ID.
     */
    private int transferTypeId;

    /**
     * The property used to contain the transfer's type description.
     */
    private String transferTypeDescription;

    /**
     * This method returns the transfer's type ID.
     *
     * @return the transfer's type ID.
     *
     */
    public int getTransferTypeId() { return transferTypeId; }

    /**
     * This method retrieves and stores the transfer's type ID.
     *
     * @param transferTypeId Stores the passed in transfer's type ID.
     *
     */
    public void setTransferTypeId(int transferTypeId) { this.transferTypeId = transferTypeId; }

    /**
     * This method returns the transfer type description.
     *
     * @return the transfer type's description.
     *
     */
    public String getTransferTypeDescription() { return transferTypeDescription; }

    /**
     * This method retrieves and stores the transfer's type description.
     *
     * @param transferTypeDescription Stores the passed in transfer's type description.
     *
     */
    public void setTransferTypeDescription(String transferTypeDescription) { this.transferTypeDescription = transferTypeDescription; }
}
