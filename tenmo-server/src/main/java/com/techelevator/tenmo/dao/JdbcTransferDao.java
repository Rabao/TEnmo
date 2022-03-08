package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the common functionality needed
 * to retrieve transfers from the database.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

@Component
public class JdbcTransferDao implements TransferDao {

    /**
     * This property and constructor are used to initialize
     * and wire the JdbcTemplate.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void jdbcTransferTypeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     *  This API returns a list of all transfers from the database.
     *
     * @return all transfers.
     *
     */
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

    /**
     *  This API returns a list of transfers associated
     *  with a specific User ID.
     *
     * @param userId Retrieves a passed-in User ID.
     * @return all transfers associated with a User ID.
     *
     */
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

    /**
     *  This API returns a transfer associated
     *  with a specific transfer ID.
     *
     * @param transId Retrieves a passed-in transfer ID.
     * @return all transfers associated with a transfer ID.
     *
     */
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

    /**
     *  This API returns a list of pending transfers associated
     *  with a specific User ID.
     *
     * @param userId Retrieves a passed-in user ID.
     * @return all pending transfers associated with a specified User ID.
     *
     */
    @Override
    public List<Transfer> getPendingTransfers(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "select * from transfers join accounts on accounts.account_id = transfers.account_from " +
                    "join transfer_statuses as ts on transfers.transfer_status_id = ts.transfer_status_id " +
                    "where user_id = ? and transfer_status_desc = 'Pending';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    /**
     *  This API POSTs a new Transfer o new transfer object to the database
     *  and then returns True if procedure is successful.
     *
     * @param trans Retrieves a passed-in Transfer object.
     * @return true if new transfer is successfully created.
     *
     */
    @Override
    public boolean createTransfer(Transfer trans) {
        String sql = "insert into transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) values (?, ?, ?, ?, ?);";
        try {
            jdbcTemplate.update(sql, trans.getTransferTypeId(), trans.getTransferStatusId(), trans.getAccountFrom(), trans.getAccountTo(), trans.getAmount());
            System.out.println("created?");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     *  This API updates a transfer to the transfer details passed by paramter transfer.
     *
     * @param transfer  Retrieves a passed-in Transfer object.
     * @return true if new transfer is successfully created.
     *
     */
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

    /**
     * This method takes and deserializes a SQL row
     * from the Account and/ or related Transfer tables, then returns
     * transfer details.
     *
     * @return user transfer details.
     *
     */
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
