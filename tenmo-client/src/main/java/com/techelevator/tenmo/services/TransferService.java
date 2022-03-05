package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * This class's APIs contain details necessary for retrieving
 * data from the Account table and securely passing it to the View.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */


public class TransferService {

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
     */
    public TransferService(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl + "/account/";
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object, passes the token to a new Entity
     * which contains the passed-in transfer, and then POSTs the
     * authenticated transfer to the database.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param trans Takes a passed-in transfer, and is then stored in an Entity.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     */
    public void createTransfer(AuthenticatedUser authenticatedUser, Transfer trans) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authenticatedUser.getToken());
        HttpEntity<Transfer> entity = new HttpEntity(trans, headers);

        try {
            restTemplate.exchange(baseUrl + "transfer/" + trans.getId(), HttpMethod.POST, entity, Transfer.class);
        } catch (RestClientResponseException e) {
            if (e.getMessage().contains("Bad funds!")) {
                System.out.println("Not enough money to transact. ");
            } else {
                System.out.println("Could not complete: " + e.getRawStatusCode());
            }
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns transfers associated with the user's ID.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param userId Takes a passed-in user ID and then appends it to the endpoint.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     * @return all transfers associated with a user's ID.
     *
     */
    public Transfer[] getTransfersFromUserId(AuthenticatedUser authenticatedUser, int userId) {
        Transfer[] transfers = null;
        try {
            transfers = restTemplate.exchange(baseUrl + "transfer/user/" + userId, HttpMethod.GET, createHttps(authenticatedUser), Transfer[].class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return transfers;
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns a transfer based on the passed-in transfer ID.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param id Takes a passed-in transfer ID and then appends it to the endpoint.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     * @return the transfer associated with the specified ID.
     *
     */
    public Transfer getTransferFromId(AuthenticatedUser authenticatedUser, int id) {
        Transfer transfer = null;
        try {
            transfer = restTemplate.exchange(baseUrl + "transfer/" + id, HttpMethod.GET, createHttps(authenticatedUser), Transfer.class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return transfer;
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns all the logged-in user's transfer records.
     *
     * @param authenticatedUser Retrieves the authentication token.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     * @return all the logged-in user's transfer records.
     *
     */
    public Transfer[] getAllTransfers(AuthenticatedUser authenticatedUser) {
        Transfer[] transfers = new Transfer[0];

        try {
            transfers = restTemplate.exchange(baseUrl + "transfer/", HttpMethod.GET, createHttps(authenticatedUser), Transfer[].class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        for(Transfer transfer : transfers){
            System.out.println("1");
        }
        return transfers;
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns all the current user's pending transfers based on the user's ID.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param id Takes the current user's passed-in user ID and then appends it to the endpoint.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     * @return all transfers currently pending based on the current user's ID.
     *
     */
    public Transfer[] getPendingTransfersByUserId(AuthenticatedUser authenticatedUser, int id) {
        Transfer[] transfers = null;
        try {
            transfers = restTemplate.exchange(baseUrl + "transfer/user/" + id + "/pending", HttpMethod.GET, createHttps(authenticatedUser), Transfer[].class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return transfers;
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then updates a transfer based on the passed-in transfer.
     *
     * @deprecated Not for use. Functionality not necessary for application type;
     * no associated use case.
     *
     */
    public void updateTransfer(AuthenticatedUser authenticatedUser, Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authenticatedUser.getToken());
        HttpEntity<Transfer> entity = new HttpEntity(transfer, headers);


        try {
            restTemplate.exchange(baseUrl + "transfer/" + transfer.getId(), HttpMethod.PUT, entity, Transfer.class);
        } catch (RestClientResponseException e) {
            if (e.getMessage().contains("Bad funds!")) {
                System.out.println("Not enough money to transact. ");
            } else {
                System.out.println("Could not complete: " + e.getRawStatusCode());
            }
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }

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
