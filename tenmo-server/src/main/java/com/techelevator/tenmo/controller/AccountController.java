package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/account")
@PreAuthorize("IsAuthenticated()")
public class AccountController {

    private UserDao userDao;
    private AccountDao accountDao;

    public AccountController(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public Account[] getAccounts() { return accountDao.listAccounts(); }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public Account getAccount(@PathVariable int id){
        return accountDao.getAccount(id);
    }

    @GetMapping(path = "/getUser/{id}")
    @PreAuthorize("hasRole('USER')")
    public String getUsername(@PathVariable int id) { return accountDao.getUsernameByAccount(id); }

    @PostMapping(path = "/transfer")
    public void createTrans(){

    }
}
