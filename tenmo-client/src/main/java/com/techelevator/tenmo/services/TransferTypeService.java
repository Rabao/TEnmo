package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.TransferType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferTypeService {
    private String baseUrl;
    private RestTemplate restTemplate;

    public TransferTypeService(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl + "/account/";
    }

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

    private HttpEntity createHttps(AuthenticatedUser authenticatedUser){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity(httpHeaders);

        return entity;
    }


}
