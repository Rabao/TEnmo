package com.techelevator.tenmo.model;

/**
 * This model class contains details necessary for storing data
 * retrieved from the database Transfer Types table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class TransferType {
    private int transferTypeId;
    private String transferTypeDescription;

    public int getTransferTypeId() { return transferTypeId; }
    public void setTransferTypeId(int transferTypeId) { this.transferTypeId = transferTypeId; }

    public String getTransferTypeDescription() { return transferTypeDescription; }
    public void setTransferTypeDescription(String transferTypeDescription) { this.transferTypeDescription = transferTypeDescription; }
}
