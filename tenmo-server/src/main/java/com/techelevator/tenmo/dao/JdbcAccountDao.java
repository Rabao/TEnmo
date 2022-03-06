package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * This class implements the common functionality needed
 * to retrieve user accounts from the database.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

@Component
public class JdbcAccountDao implements AccountDao{

    /**
     * This property and constructor are used to initialize
     * and wire the JdbcTemplate.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * This API returns an account's balance based on the username.
     *
     * @param username  Retrieves a passed-in Username.
     * @return User's account balance.
     *
     */
    @Override
    public double getBalance(String username) {
        double bal = 0;
        String sql = "select balance from accounts join users on accounts.user_id = users.user_id where username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if(results.next()){
            bal  = results.getDouble("balance");
        }
        return bal;
    }

    /**
     * This API returns an account based on the specified User ID.
     *
     * @param userId Retrieves a passed-in User's ID.
     * @return account specified by user's ID.
     *
     */
    @Override
    public Account getAccountByUserID(int userId) {
        Account account = null;
        String sql = "select * from accounts where user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if(results.next()){
            account = mapRowToAccount(results);
        }
        return account;
    }

    /**
     * This API returns an account based on the specified account ID.
     *
     * @param accId  Retrieves a passed-in account ID.
     * @return account specified by account ID.
     *
     */
    @Override
    public Account getAccountByAccountID(int accId) {
        Account account = null;
        String sql = "select * from accounts where account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accId);
        if(results.next()){
            account = mapRowToAccount(results);
        }
        return account;

    }

    /**
     * This API selects and updates an account balance based on the account's ID.
     *
     * @param acc Retrieves a passed-in account object.
     * @return True if an update was successfully made.
     *
     */
    @Override
    public boolean changeAccount(Account acc) {
        String sql = "update accounts set balance = ? where account_id = ?;";
        try {
            jdbcTemplate.update(sql, acc.getBalance(), acc.getAccount_id());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * This method takes and deserializes a SQL row
     * from the Account and/ or Users tables, then returns
     * account details.
     *
     * @return user account details.
     *
     */
    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(rs.getDouble("balance"));
        return account;
    }
}
