package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/account")
@PreAuthorize("IsAuthenticated()")
public class AccountController {

    private UserDao userDao;
    private AccountDao accountDao;

    @GetMapping(path = "/balance/{id}")
    @PreAuthorize("hasRole('USER')")
    public double getBalance(@PathVariable int id){
        return accountDao.getAccount(id).getBalance();
    }

    @PostMapping(path = "/transfer")
    public void createTrans(){

    }
}
