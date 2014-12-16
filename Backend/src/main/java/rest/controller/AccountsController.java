package rest.controller;

import components.AuthHelper;
import config.Routes;
import core.domain.Account;
import core.exceptions.APIException;
import core.services.interfaces.AccountServiceInterface;
import core.services.interfaces.CustomerServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Unlike traditional Controllers in an MVC architecture this is a RESTful
 * controller. 
 * This means that it does not have a UI associated with it.
 * The purpose of this controller is to manage accounts on the web application.
 * 
 * When requests are made to the application Route.ACCOUNTS dependent on the HTTP
 * method and URL after the initial accounts route will dictate which method is 
 * executed.
 * 
 * This controller can:
 *  - Get all accounts
 *  - Get a single account
 */
@RestController
@RequestMapping(Routes.ACCOUNTS)
@Secured("ROLE_USER")
public class AccountsController {
    
    /** AccountServiceInterface instance from Bean on startup*/
    @Autowired
    private AccountServiceInterface accountService;
    
    /** CustomerServiceInterface instance from Bean on startup */
    @Autowired
    private CustomerServiceInterface customerService;

    /**
     * When a user makes a GET request to this URL we want to return a list of
     * all their accounts.
     *
     * @param auth the OAuth Authentication for a user's permissions
     *
     * @return All of this user's accounts.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts(@AuthenticationPrincipal OAuth2Authentication auth) throws APIException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;

        //If the user has read permissions.
        if (!AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            throw new APIException("No read permissions.");
        }
        
        String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);
        List<Account> accounts = accountService.requestAllAccounts(customerId);
        
        return new ResponseEntity(accounts, headers, status);

    }

    /**
     * When a user makes a GET request to this URL we want to return the details
     * of a single customer account.
     *
     * @param auth the OAuth Authentication for a user's permissions
     * @param accountNumber the accountNumber that we want to retrieve
     * @return The Account details as JSON.
     */
    @RequestMapping(value = Routes.SINGLE_ACCOUNT, method = RequestMethod.GET)
    public ResponseEntity<Account> getCustomerAccount(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("account_number") String accountNumber) throws APIException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        
        //If the user has read permissions.
        if (!AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            throw new APIException("No read permissions.");
        }
            
        //Get the customer ID from the auth request.
        String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);
        Account account = accountService.requestAccountDetailsFromNumber(accountNumber);
        String accountCustomerId = account.getCustomerId();
        
        //Check that the customer owns this account.
        if (!accountCustomerId.equals(customerId)) {
            throw new APIException("Customer does not own this account.");
        }

        return new ResponseEntity(account, headers, status);
    }
}
