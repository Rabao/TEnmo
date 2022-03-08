package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * This class's APIs contain details necessary for retrieving
 * data from the User table and securely passing it to the View.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */


public class UserService {

    /**
     * This property is used to store the server's URL.
     */
    private String baseUrl;

    /**
     * This property is used to access REST APIs using a template object.
     */
    private RestTemplate restTemplate;

    /**
     * This constructor is used to initialize the REST template and
     * pass the server's URL and the specified endpoint to subsequent APIs.
     *
     * @param baseUrl Retrieves the API base Url.
     *
     */
    public UserService(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl + "/account/";
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns all registered users to the specified endpoint.
     *
     * @param authenticatedUser Retrieves the authentication token.
     *
     * @return all registered users.
     *
     * @exception RestClientResponseException Throws an exception if process could not be completed.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     */
    public User[] getAllUsers(AuthenticatedUser authenticatedUser){
        User[] users = null;

        try {
            users = restTemplate.exchange(baseUrl + "getUsers", HttpMethod.GET, createHttps(authenticatedUser), User[].class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return users;

    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns a specific user based on the ID passed to the endpoint.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param id Takes a passed-in ID and appends it to the endpoint.
     *
     * @return the user specified by their user ID.
     *
     * @exception RestClientResponseException Throws an exception if process could not be completed.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     */
    public User getUserById(AuthenticatedUser authenticatedUser, int id){
        User user = null;
        try {
            user = restTemplate.exchange(baseUrl + "getUsers/" + id, HttpMethod.GET, createHttps(authenticatedUser), User.class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }

        return user;
    }

    /**
     * This API retrieves the token passed by the authenticatedUser object,
     * authenticates the token, and then passes the authenticated token to
     * an Entity.
     *
     * @param authenticatedUser Retrieves the authentication token.
     *
     * @return authenticated token as an HttpEntity.
     *
     */
    private HttpEntity createHttps(AuthenticatedUser authenticatedUser){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity(httpHeaders);

        return entity;
    }
}
