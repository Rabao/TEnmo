package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

/**
 * This interface defines common functionality needed
 * to retrieve user credentials.
 *
 * @author Techelevator, Jayden Southworth, Kadeam Howell
 *
 */

public interface UserDao {

    List<User> findAll();

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);

    User getUserById(int id);
}
