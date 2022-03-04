package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * This class's APIs contain details necessary for retrieving
 * data from the Transfer Types table and securely passing it to the View.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class TransferTypeService {

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
    public TransferTypeService(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl + "/account/";
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns a transfer's type based on the filtered description
     * passed into the desc parameter.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param desc Takes a passed-in transfer type description and appends it to the endpoint filter.
     *
     * @return transfer types matching the passed-in description.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     */
    public TransferType getTransferType(AuthenticatedUser  authenticatedUser, String desc){
        TransferType transferType = null;

        try {
            String url = baseUrl + "transfer/type/filter?desc=" + desc;
            transferType = restTemplate.exchange(url, HttpMethod.GET, createHttps(authenticatedUser), TransferType.class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return transferType;
    }

    /**
     * This API uses the HttpEntity API to retrieve the token passed
     * by the authenticatedUser object,
     * then returns a transfer's type based on the transfer's type ID.
     *
     * @param authenticatedUser Retrieves the authentication token.
     * @param transId Takes a passed-in transfer type ID and appends it to the endpoint.
     *
     * @return transfer types matching the passed-in transfer type ID.
     *
     * @exception RestClientResponseException Throws an exception if RestClient cannot process the request due
     * to an issue from the client.
     * @exception ResourceAccessException Throws an exception if connection to the server is disturbed.
     *
     */
    public TransferType getTransferFromId(AuthenticatedUser authenticatedUser, int transId){
        TransferType transferType = null;

        try {
            String url = baseUrl + "transfer/type/" + transId;
            transferType = restTemplate.exchange(url, HttpMethod.GET, createHttps(authenticatedUser), TransferType.class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return transferType;
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
