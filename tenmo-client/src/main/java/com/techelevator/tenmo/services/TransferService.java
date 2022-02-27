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

public class TransferService {
        private String baseUrl;
        private RestTemplate restTemplate;

        public TransferService(String baseUrl) {
            this.restTemplate = new RestTemplate();
            this.baseUrl = baseUrl + "/account/";
        }

        public void createTransfer(AuthenticatedUser authenticatedUser, Transfer trans) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(authenticatedUser.getToken());
            HttpEntity<Transfer> entity = new HttpEntity(trans, headers);

            try {
                restTemplate.exchange(baseUrl + "transfer", HttpMethod.POST, entity, Transfer.class);
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

        public Transfer[] getTransfersFromUserId(AuthenticatedUser authenticatedUser, int userId) {
            Transfer[] transfers = null;
            try {
                transfers = restTemplate.exchange(baseUrl + "user/" + userId, HttpMethod.GET, createHttps(authenticatedUser), Transfer[].class).getBody();
            } catch (RestClientResponseException e){
                System.out.println("Could not complete: " + e.getRawStatusCode());
            } catch (ResourceAccessException e){
                System.out.println("Could not complete. Server network problem. Try again. ");
            }
            return transfers;
        }

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

        public Transfer[] getAllTransfers(AuthenticatedUser authenticatedUser) {
            Transfer[] transfers = new Transfer[0];

            try {
                transfers = restTemplate.exchange(baseUrl + "transfer/", HttpMethod.GET, createHttps(authenticatedUser), Transfer[].class).getBody();
            } catch (RestClientResponseException e){
                System.out.println("Could not complete: " + e.getRawStatusCode());
            } catch (ResourceAccessException e){
                System.out.println("Could not complete. Server network problem. Try again. ");
            }
            return transfers;
        }

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

        public void updateTransfer(AuthenticatedUser authenticatedUser, Transfer transfer) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(authenticatedUser.getToken());
            HttpEntity<Transfer> entity = new HttpEntity(transfer, headers);

            try {
                restTemplate.exchange(baseUrl + "transfer/" + transfer.getTransferId(), HttpMethod.PUT, entity, Transfer.class);
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

        private HttpEntity createHttps(AuthenticatedUser authenticatedUser){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBearerAuth(authenticatedUser.getToken());
            HttpEntity entity = new HttpEntity(httpHeaders);

            return entity;
        }
}
