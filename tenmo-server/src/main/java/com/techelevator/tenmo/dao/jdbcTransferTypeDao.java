package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class jdbcTransferTypeDao implements TransferDao {

    @Override
    public List<Transfer> getAllTransfers() {
        return null;
    }

    @Override
    public List<Transfer> getTransfersByUserId(int userId) {
        return null;
    }

    @Override
    public Transfer getTransferByTransId(int transId) {
        return null;
    }

    @Override
    public List<Transfer> getPendingTransfers(int userId) {
        return null;
    }

    @Override
    public boolean createTransfer(Transfer trans) {
        return false;
    }

    @Override
    public boolean changeTransfer(Transfer transfer) {
        return false;
    }
}
