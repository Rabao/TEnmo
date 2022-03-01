package com.techelevator.tenmo.model;

/**
 * This model class contains details necessary for storing data
 * retrieved from the database User table.
 *
 * @author Techelevator
 *
 */


public class User {

	/**
	 * The property used to contain the user's unique ID.
	 */
	private Integer id;

	/**
	 * The property used to contain the user's username.
	 */
	private String username;

	/**
	 * This method returns the user's ID when invoked.
	 *
	 * @return the user's ID stored within the object's ID property.
	 *
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method retrieves and stores the user's ID when invoked.
	 *
	 * @Param id Sets the object's id property with passed-in data.
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @Param username Sets the object's username property with passed-in data.
	 *
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
