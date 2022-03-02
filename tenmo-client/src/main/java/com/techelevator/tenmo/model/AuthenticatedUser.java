package com.techelevator.tenmo.model;

/**
 * This model class contains details necessary for storing a
 * specified user's auth token passed by the server on login.
 *
 * @author Techelevator
 *
 */

public class AuthenticatedUser {

	/**
	 * The property used to contain the user's token upon authentication.
	 */
	private String token;

	/**
	 * The property used to contain the authenticated user's object.
	 */
	private User user;

	/**
	 * This method returns the authenticated user's token.
	 *
	 * @return the authenticated user's token.
	 *
	 */
	public String getToken() {
		return token;
	}

	/**
	 * This method retrieves and stores the authenticated user's token.
	 *
	 * @param token Stores the passed in authenticated user's token in a string.
	 *
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * This method returns the authenticated user.
	 *
	 * @return the object containing the authenticated user's User object.
	 *
	 */
	public User getUser() {
		return user;
	}

	/**
	 * This method retrieves and stores the authenticated user's
	 * object when invoked.
	 *
	 * @param user Stores the passed in authenticated user's properties in a User object.
	 *
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
