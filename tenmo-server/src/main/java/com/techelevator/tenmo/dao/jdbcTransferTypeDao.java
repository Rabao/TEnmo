package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * This class implements the common functionality needed
 * to retrieve transfer types from the database.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */
@Component
public class jdbcTransferTypeDao implements TransferTypeDao {

    /**
     * This property and constructor are used to initialize
     * and wire the JdbcTemplate.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public jdbcTransferTypeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     *  This API returns the Transfer type from the database
     *  based on the passed-in description.
     *
     * @param desc The passed-in transfer type description.
     *
     * @return a TransferType from the database.
     *
     */
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

    /**
     *  This API returns the Transfer type from the database
     *  based on the passed-in ID.
     *
     * @param transId The passed-in transfer type ID.
     *
     * @return a TransferType from the database.
     *
     */
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
