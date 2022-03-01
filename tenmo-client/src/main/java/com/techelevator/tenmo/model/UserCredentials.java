package com.techelevator.tenmo.model;

/**
 * This model class contains properties necessary for storing a
 * specified user's login credentials on registration for database input.
 *
 * @author Techelevator
 *
 */

public class UserCredentials {

    /**
     * The property used to contain the user's username.
     */
    private String username;

    /**
     * The property used to contain the user's password.
     */
    private String password;

    public UserCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

