package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {
    Account getAccount(int id);

    Account[] listAccounts();

    String getUsernameByAccount(int userId);
}