package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferType;

/**
 * This interface defines common functionality needed
 * to retrieve transfer types.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public interface TransferTypeDao {
    TransferType getTransferTypeFromDesc(String desc);
    TransferType getTransferTypeFromId(int transId);
}
