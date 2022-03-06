package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;

/**
 * This interface defines common functionality needed
 * to retrieve transfer statuses.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public interface TransferStatusDao {

    TransferStatus getTransStatusByDesc(String desc);

    TransferStatus getTransferByStatusId(int transStatusId);
}
