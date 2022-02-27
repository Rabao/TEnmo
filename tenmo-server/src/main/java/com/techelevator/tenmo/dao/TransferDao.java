package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
  List<Transfer> getAllTransfers();

  List<Transfer> getTransfersByUserId(int userId);

  Transfer getTransferByTransId(int transId);

  List<Transfer> getPendingTransfers(int userId);

  boolean createTransfer(Transfer trans);

  boolean changeTransfer(Transfer transfer);

}
