package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.exceptions.BadFunds;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferType;
import com.techelevator.tenmo.model.User;
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

    private UserDao userDao;
    private AccountDao accountDao;
    private TransferDao transferDao;
    private TransferTypeDao transferTypeDao;
    private TransferStatusDao transferStatusDao;

    public AccountController(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @GetMapping(path = "/balance")
    @PreAuthorize("hasRole('USER')")
    public Double getBalance(Principal principal){
        return accountDao.getBalance(principal.getName());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/transfer/{id}")
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

        accountDao.changeAccount(sender);
        accountDao.changeAccount(receiver);

    }

    @GetMapping(path = "/transfer/type/filter")
    public TransferType getTransferByDesc(@RequestParam String desc){
        return transferTypeDao.getTransferTypeFromDesc(desc);
    }

    @GetMapping(path = "/user/{id}")
    public Account getAccByUserId(@PathVariable int id){
        return accountDao.getAccountByUserID(id);
    }

    @GetMapping(path = "/{id}")
    public Account getAccFromAccId(@PathVariable int id){
        return accountDao.getAccountByAccountID(id);
    }

    @GetMapping(path = "/transfer/user/{userId}")
    public List<Transfer> getTransByUserId(@PathVariable int userId){
        return transferDao.getTransfersByUserId(userId);
    }

    @GetMapping(path = "/transfer/{id}")
    public Transfer getTransbyId(@PathVariable int id){
        return transferDao.getTransferByTransId(id);
    }

    @GetMapping(path = "/transfer")
    public List<Transfer> getAllTransfers(){
        return transferDao.getAllTransfers();
    }

    @GetMapping(path = "/transfer/type/{id}")
    public TransferType getTransTypeFromId(@PathVariable int id){
        return transferTypeDao.getTransferTypeFromId(id);
    }

    @GetMapping(path = "/transfer/user/{userId}/pending")
    public List<Transfer> getPendingTransByUserId(@PathVariable int userId){
        return transferDao.getPendingTransfers(userId);
    }

    @PutMapping(path = "/transfer/{id}")
    public void changeTransStatus(@RequestBody @Valid Transfer trans, @PathVariable int id ) throws BadFunds{
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
