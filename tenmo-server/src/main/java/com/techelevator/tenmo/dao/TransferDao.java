package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    List<Transfer> findAll();

    Transfer findTranserById(int id);

    Transfer findTransferByAccountTo(int accountTo);

    Transfer findTransferByAccountFrom(int accountFrom);


}
