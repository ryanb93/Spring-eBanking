package rest.controller;

import components.AuthHelper;
import config.Routes;
import core.domain.Account;
import core.domain.Transaction;
import core.exceptions.APIException;
import core.services.interfaces.AccountServiceInterface;
import core.services.interfaces.CustomerServiceInterface;
import core.services.interfaces.TransactionServiceInterface;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Unlike traditional Controllers in an MVC architecture this is a RESTful
 * controller. 
 * 
 * This means that it does not have a UI associated with it.
 * The purpose of this controller is to manage transactions on 
 * the web application.
 * 
 * This controller performs the following:
 *  - Gets a collection of Transactions
 *  - Getting a single specific Transaction
 *  - Creating a new Transaction
 *  - Checks to see a customer owns the account before a transaction occurs
 */
@RestController
@RequestMapping(Routes.TRANSACTIONS)
@Secured("ROLE_USER")
public class TransactionsController {
    
    /** TransactionServiceInterface instance from Bean on startup */
    @Autowired
    private TransactionServiceInterface transactionService;
    
    /** CustomerServiceInterface instance from Bean on startup */
    @Autowired
    private CustomerServiceInterface customerService;
    
    /** AccountServiceInterface instance from Bean on startup */
    @Autowired
    private AccountServiceInterface accountService;

    /**
     * This method will return a collection of transactions for a given customers
     * account. 
     * Method occurs when a GET request occurs on this Route. 
     * It also implements a paging system, by restricting the number of transactions
     * returned. By default from the front-end it will request 10, however this number
     * is completely flexible. 
     * 
     * @param auth the OAuth Authentication for a user's permissions
     * @param accountNumber the Account Number of the Account whose Transactions we want to retrieve
     * @param request the HTTP Request for all Transactions to be retrieved
     * @return ResponseEntity<List<Transaction>> A Response with a List of all transactions linked to the Account specifiec
     * @throws core.exceptions.APIException
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getAllTransactions(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("account_number") String accountNumber,
            HttpServletRequest request) throws APIException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        
        //If the user has read permissions.
        if (!AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            throw new APIException("No read permissions.");
        }

        //If this user's customer owns the account given.
        if (!authCustomerOwnsAccount(auth, accountNumber)) {
            throw new APIException("Account is not owned by this customer.");
        }
        
        //  Get the page parameter.
        String pageParam = request.getParameter("page");
        //  Set page to 0.
        int pageInt = 0;
        //  Attempt to convert the intrusted input from the URL into an int.
        try {
            pageInt = Integer.parseInt(pageParam);
        } catch (NumberFormatException e) {
            //  If the value given was not an int then return bad request.
            throw new APIException("Invalid page parameter.");
        }
            
        List<Transaction> transactions = transactionService.requestAllTransactions(accountNumber, pageInt);

        //  Return transactions to the user.
        return new ResponseEntity(transactions, headers, status);
    }

    /**
     * Method creates a new transaction for a given customers account. 
     * This method is executed when a POST request to this Route is received.
     * 
     * @param auth the OAuth Authentication for a user's permissions
     * @param accountNumber the Account Number of the Account we want to save a new Transaction to
     * @param transaction the Transaction we want to save and link to the account specified
     * @return ResponseEntity<Transaction> A response with confirmation of a failure or success to save the Transaction
     * @throws core.exceptions.APIException
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transaction> createNewTransaction(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("account_number") String accountNumber,
            @RequestBody Transaction transaction) throws APIException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;

        //If this user has permissions to write.
        if (!AuthHelper.CAN_WRITE_FROM_AUTH(auth)) {
            throw new APIException("No write permissions.");
        }
        //If this user's customer owns the account given.
        if (!authCustomerOwnsAccount(auth, accountNumber)) {
            throw new APIException("Account is not owned by this customer.");
        }
        //If the account is same as recipient account.
        if(transaction.getOtherAccountNumber().equals(accountNumber)) {
            throw new APIException("Can't send money to the same account.");
        }
        
        //Set the transaction account ID to the path variable.
        transaction.setAccountNumber(accountNumber);
        //Set the date to current date.
        transaction.setDate(new Date());
        //All transactions from the API are "send" requests.
        transaction.setSending(true);
        //Clear the transaction ID just incase someone tried to set it already.
        transaction.clearTransactionId();
        
        //Add the transaction to the system.
        Transaction newTransaction = transactionService.requestNewTransaction(transaction);

        return new ResponseEntity(newTransaction, headers, status);
    }

    /**
     * This method returns a single transaction for a given customers account. 
     * When the Routes.TRANSACTIONS_ID receives a GET request this method is called
     * 
     * @param auth the OAuth authentication for a user's permissions
     * @param transactionId the ID of the Transaction we want to retrieve
     * @return ResponseEntity<Transaction> A Response containing the Transaction we have retrieved
     * @throws core.exceptions.APIException
     */
    @RequestMapping(value = Routes.TRANSACTIONS_ID, method = RequestMethod.GET)
    public ResponseEntity<Transaction> getSingleTransaction(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("transaction_id") String transactionId) throws APIException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;

        //If this user has permissions to read.
        if (!AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            throw new APIException("No read permissions.");
        }
        
        //Get details for the transactions.
        Transaction transaction = transactionService.requestTransactionDetails(transactionId);
        
        //If this user's customer owns the account given.
        if (!authCustomerOwnsAccount(auth, transaction.getAccountNumber())) {
            throw new APIException("Transaction is not owned by this customer.");
        }        
        
        return new ResponseEntity(transaction, headers, status);
    }

    /**
     * Helper method that checks to see that the OAuth session user owns the 
     * requested resource before any processing occurs. 
     * 
     * @param auth the OAuth Authentication for a user's permissions
     * @param accountId the Account ID of the Account we want to check authorisation for
     * @return boolean returns true if the User is authorised to view the resource
     */
    private boolean authCustomerOwnsAccount(OAuth2Authentication auth, String accountId) throws APIException {
        boolean result = false;
        //Get the current customer ID.
        String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);
        Account account = accountService.requestAccountDetailsFromNumber(accountId);

        if (account != null) {
            if (account.getCustomerId().equalsIgnoreCase(customerId)) {
                result = true;
            }
        }
        return result;
    }

}
