package com.techelevator.tenmo.dao;

/**
 * This interface defines common functionality needed
 * to retrieve accounts and their balances.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {
    double getBalance(String user);
    Account getAccountByUserID(int userId);
    Account getAccountByAccountID(int accId);

    boolean changeAccount(Account acc);

}
