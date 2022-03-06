package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * This class implements the common functionality needed
 * to retrieve transfer statuses from the database.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

@Component
public class jdbcTransferStatusDao implements TransferStatusDao{

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
     *  This API returns the Transfer Status from the database
     *  based on the passed-in description.
     *
     * @param desc The passed-in transfer status description.
     *
     * @return a TransferStatus from the database.
     *
     */
    @Override
    public TransferStatus getTransStatusByDesc(String desc) {
        TransferStatus transferStatus = null;
        String sql = "select * from transfer_statuses where transfer_status_desc = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, desc);
        if (result.next()) {
            int transferStatusID = result.getInt("transfer_status_id");
            String transferStatusDesc = result.getString("transfer_status_desc");
            transferStatus = new TransferStatus(transferStatusID, transferStatusDesc);

        }

        return transferStatus;
    }

    /**
     *  This API returns the Transfer Status from the database
     *  based on the passed-in ID.
     *
     * @param transStatusId The passed-in transfer status ID.
     *
     * @return a TransferStatus from the database.
     *
     */
    @Override
    public TransferStatus getTransferByStatusId(int transStatusId) {
        TransferStatus transferStatus = null;
        String sql = "select * from transfer_statuses where transfer_status_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transStatusId);
        if(result.next()) {
            int transferStatusId2 = result.getInt("transfer_status_id");
            String transferStatusDesc = result.getString(("transfer_status_desc"));
            transferStatus = new TransferStatus(transferStatusId2, transferStatusDesc);
        }

        return transferStatus;
    }
}
