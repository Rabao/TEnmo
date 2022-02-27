package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public void jdbcTransferTypeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Transfer> getAllTransfers() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "select * from transfers; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public List<Transfer> getTransfersByUserId(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "select * from transfers join accounts on accounts.account_id = transfers.account_from or accounts.account_id = transfers.account_to where user_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    @Override
    public Transfer getTransferByTransId(int transId) {
        Transfer trans = null;
        String sql = "select * from transfers where transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transId);
        if(results.next()){
            trans = mapRowToTransfer(results);
        }
        return trans;
    }

    @Override
    public List<Transfer> getPendingTransfers(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "select * from transfers join accounts on accounts.account_id = transfers.account_from" +
                "join transfer_statuses on transfers.transfer_status_id = transfer_statuses.transfer_status_id " +
                "where user_id = ? and transfer_status_desc = 'Pending';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    @Override
    public boolean createTransfer(Transfer trans) {
        String sql = "insert into transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) values (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, trans.getId(), trans.getTransferTypeId(), trans.getTransferStatusId(), trans.getAccountFrom(), trans.getAccountTo(), trans.getAmount());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean changeTransfer(Transfer transfer) {
        String sql = "update transfers set transfer_status_id = ? where transfer_id = ?; ";
        try {
            jdbcTemplate.update(sql, transfer.getTransferStatusId(), transfer.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt(("account_from")));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getDouble("amount"));
        return transfer;
    }

}
