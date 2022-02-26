package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/account")
@PreAuthorize("IsAuthenticated()")
public class AccountController {


    @GetMapping(path = "balance/{id}")
    @PreAuthorize("hasRole('USER')")
    public void getBalance(@PathVariable int id){

    }

    @PostMapping(path = "/transfer")
    public void createTrans(){

    }
}
