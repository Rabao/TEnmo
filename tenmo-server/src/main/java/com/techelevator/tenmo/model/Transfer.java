package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;

public class Transfer {


    private int id;

    @NotBlank
    private int transferTypeId;

    @NotBlank
    private int transferStatusId;

    @NotBlank
    private int accountFrom;

    @NotBlank
    private int accountTo;

    @NotBlank
    private double amount;

    public Transfer() {}

    public int getId() { return id; }
    public int getTransferTypeId() { return transferTypeId; }
    public int getTransferStatusId() { return transferStatusId; }
    public int getAccountFrom() { return accountFrom; }
    public int getAccountTo() { return accountTo;}
    public double getAmount() { return amount; }

    public void setId(int id) { this.id = id; }
    public void setTransferTypeId(int transferTypeId) { this.transferTypeId = transferTypeId; }
    public void setTransferStatusId(int transferStatusId) { this.transferStatusId = transferStatusId; }
    public void setAccountFrom(int accountFrom) { this.accountFrom = accountFrom; }
    public void setAccountTo(int accountTo) { this.accountTo = accountTo; }
    public void setAmount(double amount) { this.amount = amount; }

    public Transfer(int id, int transferTypeId, int transferStatusId, int accountFrom, int accountTo, double amount) {
        this.id = id;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }
}
