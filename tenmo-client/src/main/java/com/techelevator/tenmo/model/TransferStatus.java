package com.techelevator.tenmo.model;

/**
 * This model class contains details necessary for storing data
 * retrieved from the database Transfer Statuses table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class TransferStatus {
    private int transferStatusId;
    private String transferStatusDesc;

    public int getTransferStatusId() { return transferStatusId; }
    public void setTransferStatusId(int transferStatusId) { this.transferStatusId = transferStatusId; }

    public String getTransferStatusDesc() { return transferStatusDesc; }
    public void setTransferStatusDesc(String transferStatusDesc) { this.transferStatusDesc = transferStatusDesc; }
}
