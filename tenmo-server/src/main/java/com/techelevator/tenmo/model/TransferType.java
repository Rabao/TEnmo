package com.techelevator.tenmo.model;

/**
 * This DTO contains details necessary for interfacing with the database's TransferType table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class TransferType {
    private int transferTypeId;
    private String transferDesc;

    public TransferType(int transferTypeId, String transferDesc) {
        this.transferTypeId = transferTypeId;
        this.transferDesc = transferDesc;
    }

    public int getTransferTypeId() { return transferTypeId; }
    public void setTransferTypeId(int transferTypeId) {this.transferTypeId = transferTypeId; }

    public String getTransferDesc() { return transferDesc; }
    public void setTransferDesc(String transferDesc) { this.transferDesc = transferDesc; }
}
