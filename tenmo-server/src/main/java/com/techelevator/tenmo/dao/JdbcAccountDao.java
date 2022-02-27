package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcAccountDao implements AccountDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


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


    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getInt("account_id"));
        account.setUser_id(rs.getInt("user_id"));
        account.setBalance(rs.getDouble("balance"));
        return account;
    }
}
