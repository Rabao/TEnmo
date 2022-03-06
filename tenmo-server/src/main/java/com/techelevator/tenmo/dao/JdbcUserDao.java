package com.techelevator.tenmo.dao;


/**
 * This class implements the common functionality needed
 * to retrieve user credentials from the database.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    /**
     * The property used to initialize the starting balance.
     */
    private static final BigDecimal STARTING_BALANCE = new BigDecimal("1000.00");

    /**
     * This property and constructor are used to initialize
     * and wire the JdbcTemplate.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public JdbcUserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     *  This API returns the User's ID from the database
     *  using the passed-in username.
     *
     * @param username Retrieves passed-in username.
     * @return user's ID.
     *
     */
    @Override
    public int findIdByUsername(String username) {
        String sql = "SELECT user_id FROM users WHERE username ILIKE ?;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if (id != null) {
            return id;
        } else {
            return -1;
    }
    }

    /**
     *  This API returns a list of all Users from the database.
     *
     * @return all Users.
     *
     */
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM users;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    /**
     *  This API returns the User from the database
     *  using the passed-in username.
     *
     * @param username Retrieves passed-in username.
     * @return User object.
     *
     */
    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT user_id, username, password_hash FROM users WHERE username ILIKE ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()){
            return mapRowToUser(rowSet);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    /**
     *  This API POSTs a User's credentials to the database,
     *  then assigns the user a new account.
     *
     * @param username Retrieves passed-in username.
     * @param password Retrieves passed-in password.
     *
     * @return true if a User and their account are created.
     *
     */
    @Override
    public boolean create(String username, String password) {

        // create user
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?) RETURNING user_id; ";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        Integer newUserId;
        try {
            newUserId = jdbcTemplate.queryForObject(sql, Integer.class, username, password_hash);
            System.out.println(newUserId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }

        // create account
        sql = "INSERT INTO accounts (user_id, balance) values(?, ?)";
        try {
            System.out.println("test2");
            jdbcTemplate.update(sql, newUserId, STARTING_BALANCE);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     *  This API returns the User from the database
     *  using the passed-in ID.
     *
     * @param id Retrieves a passed-in integer value for the ID.
     *
     * @return a User object containing the passed-in ID.
     *
     */
    @Override
    public User getUserById(int id) {
        String sql = "SELECT user_id, username FROM users WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        User user = null;
        if(result.next()) {
            user = new User();
            user.setId(result.getLong("user_id"));
            user.setUsername(result.getString("username"));
        }
        return user;
    }


    /**
     * This method takes and deserializes a SQL row
     * from the Users tables, activates the user,
     * sets the Role, and then returns account details.
     *
     * @return user account details.
     *
     */
    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }
}
