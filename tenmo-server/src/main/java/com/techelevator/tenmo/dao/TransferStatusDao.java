package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;

public interface TransferStatusDao {

    TransferStatus getTransStatusByDesc(String desc);

    TransferStatus getTransferByStatusId(int transStatusId);
}
