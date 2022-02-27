package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {
    double getBalance(String user);
    Account getAccountByUserID(int userId);
    Account getAccountByAccountID(int accId);

    boolean changeAccount(Account acc);

}
