package com.techelevator.tenmo.model;

import javax.validation.constraints.NotEmpty;

/**
 * This DTO contains details necessary for interfacing with the database's Account table.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

public class RegisterUserDTO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
