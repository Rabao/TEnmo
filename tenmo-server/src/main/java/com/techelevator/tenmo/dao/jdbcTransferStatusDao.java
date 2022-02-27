package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class jdbcTransferStatusDao implements TransferStatusDao{

    private JdbcTemplate jdbcTemplate;

    public void jdbcTransferTypeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


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
