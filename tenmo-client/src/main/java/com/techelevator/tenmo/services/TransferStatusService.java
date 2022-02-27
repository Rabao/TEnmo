package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.TransferStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferStatusService {
    private String baseUrl;
    private RestTemplate restTemplate;

    public TransferStatusService(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl + "/account/";
    }

    public TransferStatus getTransferStatusByDesc(AuthenticatedUser authenticatedUser, String desc){
        TransferStatus transferStatus = null;
        try {
            String url = baseUrl + "transfer/status/filter?desc=" + desc;
            transferStatus = restTemplate.exchange(url, HttpMethod.GET, createHttps(authenticatedUser), TransferStatus.class).getBody();
        }catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return transferStatus;
    }

    public TransferStatus getTransferStatusbyId(AuthenticatedUser authenticatedUser, int id){
        TransferStatus transferStatus = null;
        try {
            String url = baseUrl + "transfer/status/" + id;
            transferStatus = restTemplate.exchange(url, HttpMethod.GET, createHttps(authenticatedUser), TransferStatus.class).getBody();
        }catch (RestClientResponseException e){
            System.out.println("Could not complete: " + e.getRawStatusCode());
        } catch (ResourceAccessException e){
            System.out.println("Could not complete. Server network problem. Try again. ");
        }
        return transferStatus;
    }

    private HttpEntity createHttps(AuthenticatedUser authenticatedUser){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity(httpHeaders);

        return entity;
    }
}
