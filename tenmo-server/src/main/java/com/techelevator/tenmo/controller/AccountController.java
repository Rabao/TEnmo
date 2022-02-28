package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.exceptions.BadFunds;
import com.techelevator.tenmo.exceptions.WrongPrincipalApproved;
import com.techelevator.tenmo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/account")
@PreAuthorize("IsAuthenticated()")
public class AccountController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransferDao transferDao;
    @Autowired
    private TransferTypeDao transferTypeDao;
    @Autowired
    private TransferStatusDao transferStatusDao;

    public AccountController(UserDao userDao, AccountDao accountDao, TransferDao transferDao, TransferTypeDao transferTypeDao, TransferStatusDao transferStatusDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.transferDao = transferDao;
        this.transferTypeDao = transferTypeDao;
        this.transferStatusDao = transferStatusDao;
    }


    @GetMapping(path = "/getUsers")
    @PreAuthorize("hasRole('USER')")
    public List<User> getUsers(){
        return userDao.findAll();
    }

    @GetMapping(path = "/getUsers/{id}")
    @PreAuthorize("hasRole('USER')")
    public User getUserById(@PathVariable int id){
        return userDao.getUserById(id);
    }

    @GetMapping(path = "/balance")
    @PreAuthorize("hasRole('USER')")
    public Double getBalance(Principal principal){
        return accountDao.getBalance(principal.getName());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transfer/{id}")
    @PreAuthorize("hasRole('USER')")
    public void initTransfer(@RequestBody @Valid Transfer transfer, @PathVariable int id) throws BadFunds {

        double transAmount = transfer.getAmount();
        Account sender = accountDao.getAccountByAccountID(transfer.getAccountFrom());
        Account receiver = accountDao.getAccountByAccountID(transfer.getAccountTo());

        double newSenderBal = sender.getBalance() - transAmount;
        if(newSenderBal >= 0){
            sender.setBalance(newSenderBal);
            receiver.setBalance(receiver.getBalance() + transAmount);
        } else {
            throw new BadFunds();
        }

        transferDao.createTransfer(transfer);

        System.out.println("Created?");

        accountDao.changeAccount(sender);
        accountDao.changeAccount(receiver);

    }

    @GetMapping(path = "/transfer/type/filter")
    @PreAuthorize("hasRole('USER')")
    public TransferType getTransferByDesc(@RequestParam String desc){
        return transferTypeDao.getTransferTypeFromDesc(desc);
    }

    @GetMapping(path = "/transfer/status/filter")
    @PreAuthorize("hasRole('USER')")
    public TransferStatus getTransferStatusByDesc(@RequestParam String desc){
        return transferStatusDao.getTransStatusByDesc(desc);
    }

    @GetMapping(path = "/transfer/status/{id}")
    @PreAuthorize("hasRole('USER')")
    public TransferStatus getTransferStatusByDesc(@PathVariable int id){
        return transferStatusDao.getTransferByStatusId(id);
    }

    @GetMapping(path = "/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public Account getAccByUserId(@PathVariable int id){
        return accountDao.getAccountByUserID(id);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public Account getAccFromAccId(@PathVariable int id){
        return accountDao.getAccountByAccountID(id);
    }

    @GetMapping(path = "/transfer/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Transfer> getTransByUserId(@PathVariable int userId){
        return transferDao.getTransfersByUserId(userId);
    }

    @GetMapping(path = "/transfer/{id}")
    @PreAuthorize("hasRole('USER')")
    public Transfer getTransbyId(@PathVariable int id){
        return transferDao.getTransferByTransId(id);
    }

    @GetMapping(path = "/transfer")
    @PreAuthorize("hasRole('USER')")
    public List<Transfer> getAllTransfers(){
        return transferDao.getAllTransfers();
    }

    @GetMapping(path = "/transfer/type/{id}")
    @PreAuthorize("hasRole('USER')")
    public TransferType getTransTypeFromId(@PathVariable int id){
        return transferTypeDao.getTransferTypeFromId(id);
    }

    @GetMapping(path = "/transfer/user/{userId}/pending")
    @PreAuthorize("hasRole('USER')")
    public List<Transfer> getPendingTransByUserId(@PathVariable int userId){
        return transferDao.getPendingTransfers(userId);
    }

    @PutMapping(path = "/transfer/{id}")
    @PreAuthorize("#username == authentication.principal.username")
    public void changeTransStatus(Principal principal, @RequestBody @Valid Transfer trans, @PathVariable int id ) throws BadFunds, WrongPrincipalApproved {

        if(trans.getTransferStatusId() == transferStatusDao.getTransStatusByDesc("Approved").getTransferStatusId()) {
            double transAmount = trans.getAmount();
            Account sender = accountDao.getAccountByAccountID(trans.getAccountFrom());
            Account receiver = accountDao.getAccountByAccountID(trans.getAccountTo());



            double newSenderBal = sender.getBalance() - transAmount;
            if(newSenderBal >= 0){
                sender.setBalance(newSenderBal);
                receiver.setBalance(receiver.getBalance() + transAmount);
            } else {
                throw new BadFunds();
            }
        } else {
            transferDao.changeTransfer(trans);
        }
    }

}
