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

/**
 * This is the web API controller class handling pathing to the Account,
 * Transfer, and Users tables.
 *
 * @author Jayden Southworth, Kadeam Howell
 *
 */

@RestController
@RequestMapping(path = "/account")
@PreAuthorize("IsAuthenticated()")
public class AccountController {

    /**
     * These properties instantiate and wire
     * Data Access Objects to the database using
     * Spring Autowire annotation.
     *
     */
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


    /**
     * This API maps to the /getUsers endpoint and returns all registered Users
     * to an authenticated user.
     *
     * @return all registered Users.
     */
    @GetMapping(path = "/getUsers")
    @PreAuthorize("hasRole('USER')")
    public List<User> getUsers(){
        return userDao.findAll();
    }

    /**
     * This API maps to the /getUser endpoint and
     * returns the specified User via its User ID
     * to an authenticated user.
     *
     * @return Users by their user ID.
     * @param id @PathVariable Retrieves the User's user ID.
     */
    @GetMapping(path = "/getUsers/{id}")
    @PreAuthorize("hasRole('USER')")
    public User getUserById(@PathVariable int id){
        return userDao.getUserById(id);
    }

    /**
     * This API maps to the /balance endpoint and
     * returns the currently authenticated User's
     * balance by searching the database for the user's name.
     *
     * @return the current User's account balance via their username.
     * @param principal Retrieves the current user's credentials.
     */
    @GetMapping(path = "/balance")
    @PreAuthorize("hasRole('USER')")
    public Double getBalance(Principal principal){
        return accountDao.getBalance(principal.getName());
    }

    /**
     * This API maps to the /transfer endpoint and
     * implements logic to POST a new transfer from
     * an authenticated user to the Transfers table.
     *
     * @param id Provides the mapping ID number.
     * @param transfer Retrieves and validates passed-in transfer details.
     *
     * @throws BadFunds If the entered amount is not greater than or equal to $0.
     */
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
        accountDao.changeAccount(sender);
        accountDao.changeAccount(receiver);

    }

    /**
     * This API maps to the transfer type filter and
     * returns all transfers with the specified description
     * to the authenticated user.
     *
     * @param desc Retrieves a Transfer based on the type description. (Sent, Requested)
     *
     * @return transfer based on the specified type description.
     *
     */
    @GetMapping(path = "/transfer/type/filter")
    @PreAuthorize("hasRole('USER')")
    public TransferType getTransferByDesc(@RequestParam String desc){
        return transferTypeDao.getTransferTypeFromDesc(desc);
    }

    /**
     * This API maps to the transfer status filter and
     * returns all transfers with the specified description
     * to the authenticated user.
     *
     * @param desc Retrieves a Transfer based on the status description. (Approved, Rejected, Pending)
     *
     * @return transfer based on the specified status description.
     *
     */
    @GetMapping(path = "/transfer/status/filter")
    @PreAuthorize("hasRole('USER')")
    public TransferStatus getTransferStatusByDesc(@RequestParam String desc){
        return transferStatusDao.getTransStatusByDesc(desc);
    }

    /**
     * This API maps to the transfer status ID endpoint and
     * returns all transfers with the specified status ID
     * to the authenticated user.
     *
     * @param id Retrieves a Transfer based on the status ID. (Approved, Rejected, Pending)
     *
     * @return transfer based on the specified status ID.
     *
     */
    @GetMapping(path = "/transfer/status/{id}")
    @PreAuthorize("hasRole('USER')")
    public TransferStatus getTransferStatusByDesc(@PathVariable int id){
        return transferStatusDao.getTransferByStatusId(id);
    }

    /**
     * This API maps to the User ID endpoint and
     * returns the account belonging to the
     * specified User ID to the authenticated user.
     *
     * @param id Retrieves the account associated with a User's ID.
     *
     * @return account associated with the specified User ID.
     *
     */
    @GetMapping(path = "/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public Account getAccByUserId(@PathVariable int id){
        return accountDao.getAccountByUserID(id);
    }

    /**
     * This API maps to the account ID endpoint and
     * returns the account belonging to the
     * specified ID to the authenticated user.
     *
     * @param id Retrieves the account by the account's ID.
     *
     * @return account associated with the specified ID.
     *
     */
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public Account getAccFromAccId(@PathVariable int id){
        return accountDao.getAccountByAccountID(id);
    }

    /**
     * This API maps to the transfer-by-user-ID endpoint and
     * returns the transfers belonging to the specified User ID
     * to an authenticated user.
     *
     * @param userId Retrieves the transfers associated with a User's ID.
     *
     * @return transfers associated with the specified User ID.
     *
     */
    @GetMapping(path = "/transfer/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Transfer> getTransByUserId(@PathVariable int userId){
        return transferDao.getTransfersByUserId(userId);
    }

    /**
     * This API maps to the transfer's ID endpoint and
     * returns the transfer belonging to the specified ID
     * to an authenticated user.
     *
     * @param id Retrieves the transfer by the transfer ID.
     *
     * @return transfer associated with the specified transfer ID.
     *
     */
    @GetMapping(path = "/transfer/{id}")
    @PreAuthorize("hasRole('USER')")
    public Transfer getTransbyId(@PathVariable int id){
        return transferDao.getTransferByTransId(id);
    }

    /**
     * This API maps to the /transfer endpoint and returns all
     * the current authenticated user's transfers.
     *
     * @return all the authenticated user's transfers.
     */
    @GetMapping(path = "/transfer")
    @PreAuthorize("hasRole('USER')")
    public List<Transfer> getAllTransfers(){
        return transferDao.getAllTransfers();
    }

    /**
     * This API maps to the transfer-type-by-ID endpoint and returns
     * the transfer type by the type ID to an authenticated user.
     *
     * @param id Retrieves the transfer type by the type ID.
     *
     * @return transfer types from the specified type ID.
     *
     */
    @GetMapping(path = "/transfer/type/{id}")
    @PreAuthorize("hasRole('USER')")
    public TransferType getTransTypeFromId(@PathVariable int id){
        return transferTypeDao.getTransferTypeFromId(id);
    }

    /**
     * This API maps to the User's pending transfers endpoint
     * and returns pending transfers that belong to a specified
     * User's ID to the authenticated user.
     *
     * @param userId Retrieves pending transfers by the User's ID.
     *
     * @return pending transfers based on the specified User ID.
     *
     */
    @GetMapping(path = "/transfer/user/{userId}/pending")
    @PreAuthorize("hasRole('USER')")
    public List<Transfer> getPendingTransByUserId(@PathVariable int userId){
        return transferDao.getPendingTransfers(userId);
    }

    /**
     * This API maps to the transfer ID endpoint and
     * implements logic to update a transfer's status
     * once it has been Approved or Rejected by the
     * correct authenticated user to the Transfers table.
     *
     * @param principal Retrieves credentials for the current authenticated user.
     * @param id Retrieves the passed-in transfer status ID.
     * @param trans Retrieves the specified transfer's details.
     *
     * @throws BadFunds If the entered amount is not greater than or equal to $0.
     *
     */
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
