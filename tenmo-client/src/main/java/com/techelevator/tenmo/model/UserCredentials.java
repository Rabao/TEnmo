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

    /**
     * This constructor takes login credentials passed into it
     * and stores them in a UserCredentials object.
     *
     * @param username Retrieves and stores the User's username.
     * @param password Retrieves and stores the User's password.
     *
     */
    public UserCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

    /**
     * This method returns the user's username when invoked.
     *
     * @return the user's username stored within the object's username property.
     *
     */
	public String getUsername() {
        return username;
    }

    /**
     * This method retrieves and stores the user's username when invoked.
     *
     * @param username Sets the object's username property with passed-in data.
     *
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method returns the user's password when invoked.
     *
     * @return the user's password stored within the object's password property.
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method retrieves and stores the user's password when invoked.
     *
     * @param password Sets the object's password property with passed-in data.
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

