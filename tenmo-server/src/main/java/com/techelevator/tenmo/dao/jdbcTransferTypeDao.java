package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Component
public class jdbcTransferTypeDao implements TransferTypeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public jdbcTransferTypeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TransferType getTransferTypeFromDesc(String desc) {
        TransferType transferType = null;
        String sql = "select * from transfer_types where transfer_type_desc = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, desc);
        if(result.next()){
            System.out.println(result.getInt("transfer_type_id"));
            int transferTypeId  = result.getInt("transfer_type_id");
            String transferTypeDesc = result.getString("transfer_type_desc");
            transferType = new TransferType(transferTypeId, transferTypeDesc);
        }
        return transferType;
    }

    @Override
    public TransferType getTransferTypeFromId(int transId) {
        TransferType transferType = null;

        String sql = "select * from transfer_types where transfer_type_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transId);
        if(result.next()) {

            int transferTypeId = result.getInt("transfer_type_id");
            String transferTypeDesc = result.getString("transfer_type_desc");

            transferType = new TransferType(transferTypeId, transferTypeDesc);
        }
        return transferType;
    }
}
