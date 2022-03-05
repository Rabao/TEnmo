package com.techelevator.tenmo.services;

import java.time.LocalDateTime;
import java.util.Map;

import com.techelevator.tenmo.exceptions.AuthenticationServiceException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserCredentials;

/**
 * This class's APIs handle user login, registration and authentication.
 *
 * @author Techelevator
 *
 */

public class AuthenticationService {

	/**
	 * This property is used to store the server's URL.
	 */
    private String baseUrl;

	/**
	 * This property is used to access REST APIs using a template object.
	 */
    private RestTemplate restTemplate = new RestTemplate();

	/**
	 * This constructor is used to pass the server's URL to subsequent APIs.
	 */
    public AuthenticationService(String url) {
        this.baseUrl = url;
    }

	/**
	 * This API takes passed-in user credentials and stores them in a
	 * HttpEntity Request Entity using the createRequestEntity() API.
	 * It then returns the entity when invoked.
	 *
	 * @param credentials Retrieves passed-in user login credentials.
	 * @throws AuthenticationServiceException on authentication failure.
	 *
	 * @return the login request Entity containing the user's login credentials.
	 *
	 */
    public AuthenticatedUser login(UserCredentials credentials) throws AuthenticationServiceException {
        HttpEntity<UserCredentials> entity = createRequestEntity(credentials);
        return sendLoginRequest(entity);
    }

	/**
	 * This API takes passed-in user credentials and stores them in a
	 * HttpEntity Request Entity. It then attempts to POST the entity
	 * using the sendRegistrationRequest() API containing the new user
	 * credentials to the Users table when invoked.
	 *
	 * @param credentials Retrieves passed-in user registration credentials.
	 * @throws AuthenticationServiceException on authentication failure.
	 *
	 *
	 */
    public void register(UserCredentials credentials) throws AuthenticationServiceException {
    	HttpEntity<UserCredentials> entity = createRequestEntity(credentials);
        sendRegistrationRequest(entity);
    }

	/**
	 * This API takes passed-in user credentials and stores them in a new
	 * HttpEntity. It then serializes the data using the JSON media type.
	 *
	 * @param credentials Retrieves passed-in user login credentials.
	 *
	 * @return the HttpEntity containing the user's login credentials.
	 *
	 */
	private HttpEntity<UserCredentials> createRequestEntity(UserCredentials credentials) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<UserCredentials> entity = new HttpEntity<>(credentials, headers);
    	return entity;
    }

	/**
	 * This API takes a passed-in user credentials Entity and passes it along
	 * to a ResponseEntity of AuthenticatedUser type. It then POSTs the credentials
	 * to the login endpoint and retrieves the authentication token.
	 *
	 * @param entity Retrieves serialized passed-in user login credentials.
	 * @throws AuthenticationServiceException on authentication failure.
	 *
	 * @return the user's authentication token and associated HttpResponse.
	 *
	 */
	private AuthenticatedUser sendLoginRequest(HttpEntity<UserCredentials> entity) throws AuthenticationServiceException {
		try {	
			ResponseEntity<AuthenticatedUser> response = restTemplate.exchange(baseUrl + "login", HttpMethod.POST, entity, AuthenticatedUser.class);
			return response.getBody(); 
		} catch(RestClientResponseException ex) {
			String message = createLoginExceptionMessage(ex);
			throw new AuthenticationServiceException(message);
        }
	}

	/**
	 * This API takes a passed-in user credentials Entity and passes it along
	 * to a ResponseEntity of Map type. It then POSTs the credentials to the
	 * register endpoint, adding the user credentials to the Users table.
	 *
	 * @param entity Retrieves serialized passed-in user registration credentials.
	 * @throws AuthenticationServiceException on authentication failure.
	 *
	 * @return associated HttpResponse.
	 *
	 */
    private ResponseEntity<Map> sendRegistrationRequest(HttpEntity<UserCredentials> entity) throws AuthenticationServiceException {
    	try {
			return restTemplate.exchange(baseUrl + "register", HttpMethod.POST, entity, Map.class);
		} catch(RestClientResponseException ex) {
			String message = createRegisterExceptionMessage(ex);
			throw new AuthenticationServiceException(message);
        }
	}

	/**
	 * This is the user login Exception handler.
	 *
	 * @param ex takes a passed-in error message.
	 *
	 * @return a message containing exception details as defined by developer
	 * or if the entered credentials are invalid.
	 *
	 */
	private String createLoginExceptionMessage(RestClientResponseException ex) {
		String message = null;
		if (ex.getRawStatusCode() == 401 && ex.getResponseBodyAsString().length() == 0) {
		    message = ex.getRawStatusCode() + " : {\"timestamp\":\"" + LocalDateTime.now() + "+00:00\",\"status\":401,\"error\":\"Invalid credentials\",\"message\":\"Login failed: Invalid username or password\",\"path\":\"/login\"}";
		}
		else {
		    message = ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString();
		}
		return message;
	}

	/**
	 * This is the user registration Exception handler.
	 *
	 * @param ex takes a passed-in error message.
	 *
	 * @return a message containing exception details as defined by developer
	 * or if the entered credentials are invalid.
	 *
	 */
	private String createRegisterExceptionMessage(RestClientResponseException ex) {
		String message = null;
		if (ex.getRawStatusCode() == 400 && ex.getResponseBodyAsString().length() == 0) {
		    message = ex.getRawStatusCode() + " : {\"timestamp\":\"" + LocalDateTime.now() + "+00:00\",\"status\":400,\"error\":\"Invalid credentials\",\"message\":\"Registration failed: Invalid username or password\",\"path\":\"/register\"}";
		}
		else {
		    message = ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString();
		}
		return message;
	}
}
