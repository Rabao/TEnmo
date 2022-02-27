package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import org.springframework.web.client.RestTemplate;

public class AccountService {
    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    public AccountService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Account getAccount(int id){
        return restTemplate.getForObject(baseUrl + "/account/balance/" + id, Account.class);
    }
}
