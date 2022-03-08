package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import io.cucumber.java.bs.A;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class's APIs contain details necessary for retrieving
 * data from the Account table and securely passing it to the View.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class AccountService {

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
     */
    public AccountService(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl + "/account/";
    }

    /**
     * This API uses the HttpEntity API to retrieve the current user,
     * and the user's token passed by the authenticatedUser object.
     *
     * @param authenticatedUser Retrieves the current user and associated details, as well as the authentication token.
     *
     * @return the current account's balance.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     */
    public double getBalance(AuthenticatedUser authenticatedUser){
        double bal = 0;

        try {
            bal = restTemplate.exchange(baseUrl + "balance", HttpMethod.GET, createHttps(authenticatedUser), double.class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return bal;
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns accounts associated with the passed-in user ID.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param userId Takes a passed-in user ID and appends it to the endpoint.
     *
     * @return accounts associated with the passed-in user ID.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     */
    public Account getAccountByUserId(AuthenticatedUser authenticatedUser, int userId){
        Account account = null;

        try {
            account = restTemplate.exchange(baseUrl + "user/" + userId, HttpMethod.GET, createHttps(authenticatedUser), Account.class).getBody();
        }catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return account;
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns an account based on the passed-in account ID.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param accId Takes a passed-in account ID and appends it to the endpoint.
     *
     * @return an account associated with the passed-in account ID.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     */
    public Account getAccountById(AuthenticatedUser authenticatedUser, int accId){
        Account account = null;
        try {
            account = restTemplate.exchange(baseUrl + accId, HttpMethod.GET, createHttps(authenticatedUser), Account.class).getBody();
        }catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return account;
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
