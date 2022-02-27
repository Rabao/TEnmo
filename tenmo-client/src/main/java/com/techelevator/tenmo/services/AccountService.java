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
        this.baseUrl = baseUrl + "/account/";
    }

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



    private HttpEntity createHttps(AuthenticatedUser authenticatedUser){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity(httpHeaders);

        return entity;
    }
}
