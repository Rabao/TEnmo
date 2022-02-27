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

public class AccountService {
    private String baseUrl;
    private RestTemplate restTemplate;

    public AccountService(String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
    }

    public Account[] getAccounts(AuthenticatedUser authenticatedUser){
        Account[] accounts = null;
        try {
            return restTemplate.exchange(baseUrl + "/account", HttpMethod.GET, createHttps(authenticatedUser), Account[].class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete. " + e.getRawStatusCode());
        } catch (ResourceAccessException e) {
            System.out.println("Could not complete. ");
        }

        return accounts;
    }

    public String getUsername(AuthenticatedUser authenticatedUser, int id){
        String username = "";
        try{
            return restTemplate.exchange(baseUrl + "/account/getUser/" + id, HttpMethod.GET, createHttps(authenticatedUser), String.class).getBody();
        }  catch (RestClientResponseException e){
            System.out.println("Could not complete. " + e.getRawStatusCode());
        } catch (ResourceAccessException e) {
            System.out.println("Could not complete. ");
        }
        return username;
    }

    public Account getAccount(AuthenticatedUser authenticatedUser) {
        Account acccount = null;

        try {
            return restTemplate.exchange(baseUrl + "/account/" + authenticatedUser.getUser().getId(), HttpMethod.GET, createHttps(authenticatedUser), Account.class).getBody();
        } catch (RestClientResponseException e){
            System.out.println("Could not complete. " + e.getRawStatusCode());
        } catch (ResourceAccessException e) {
            System.out.println("Could not complete. ");
        }

        return acccount;
    }

    private HttpEntity createHttps(AuthenticatedUser authenticatedUser){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity(httpHeaders);

        return entity;
    }
}
