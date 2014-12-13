package rest.controller;

import components.AuthHelper;
import core.domain.Transaction;
import core.services.interfaces.TransactionServiceInterface;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import config.Routes;
import core.domain.Account;
import core.exceptions.InsufficientFundsException;
import core.services.interfaces.AccountServiceInterface;
import core.services.interfaces.CustomerServiceInterface;
import java.util.List;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

/**
 * Unlike traditional Controllers in an MVC architecture this is a RESTful
 * controller. 
 * 
 * This means that it does not have a UI associated with it.
 * The purpose of this controller is to manage transactions on 
 * the web application.
 * ! Will return all responses as JSON !
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
     * returned. By default from the frontend it will request 10, however this number
     * is completely flexible. 
     * 
     * @param auth the OAuth Athentication for a user's permissions
     * @param accountNumber the Account Number of the Account whose Transactions we want to retrieve
     * @param request the HTTP Request for all Transactions to be retrieved
     * @return ResponseEntity<List<Transaction>> A Response with a List of all transactions linked to the Account specifiec
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getAllTransactions(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("account_number") String accountNumber,
            HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Transaction> transactions = null;

        //  If this user has permissions to read.
        if (AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            //  Get the page parameter.
            String pageParam = request.getParameter("page");
            //  If it is not found then use the value 0.
            if (pageParam == null) {
                pageParam = "0";
            }
            //  Set page to 0.
            int pageInt = 0;
            //  Attempt to convert the intrusted input from the URL into an int.
            try {
                pageInt = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                //  If the value given was not an int then return bad request.
                return new ResponseEntity(null, headers, HttpStatus.BAD_REQUEST);
            }
            transactions = transactionService.requestAllTransactions(accountNumber, pageInt);

        } else {
            status = HttpStatus.FORBIDDEN;
        }
        //  Return transactions to the user.
        return new ResponseEntity(transactions, headers, status);
    }

    /**
     * Method creates a new transaction for a given customers account. 
     * This method is executed when a POST request to this Route is received.
     * 
     * @param auth the OAuth Athentication for a user's permissions
     * @param accountNumber the Account Number of the Account we want to save a new Transaction to
     * @param transaction the Transaction we want to save and link to the account specified
     * @return ResponseEntity<Transaction> A response with confirmation of a faliure or success to save the Transaction
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transaction> createNewTransaction(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("account_number") String accountNumber,
            @RequestBody Transaction transaction) throws InsufficientFundsException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;
        Transaction newTransaction = null;

        //If this user has permissions to write.
        if (AuthHelper.CAN_WRITE_FROM_AUTH(auth)) {
            //If this user's customer owns the account given.
            if (authCustomerOwnsAccount(auth, accountNumber)) {
                //If the transaction value is greater than zero
                if(transaction.getValue() >= 0)  {
                    //Set the transaction account ID to the path variable.
                    transaction.setAccountNumber(accountNumber);
                    //Set the date to current date.
                    transaction.setDate(new Date());
                    //Add the transaction to the system.
//                    try {
                        newTransaction = transactionService.requestNewTransaction(transaction);
//                    }
//                    catch(InsufficientFundsException e) {
//                        status = HttpStatus.BAD_REQUEST;
//                    }
                }
                else {
                    status = HttpStatus.BAD_REQUEST;
                }
            }
            else {
                status = HttpStatus.FORBIDDEN;
            }
        } 
        else {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity(newTransaction, headers, status);
    }

    /**
     * This method returns a single transaction for a given customers account. 
     * When the Routes.TRANSACTIONS_ID receives a GET request this method is called
     * 
     * @param auth the OAuth Athentication for a user's permissions
     * @param transactionId the ID of the Transaction we want to retrieve
     * @return ResponseEntity<Transaction> A Response containing the Transaction we have retrieved
     */
    @RequestMapping(value = Routes.TRANSACTIONS_ID, method = RequestMethod.GET)
    public ResponseEntity<Transaction> getSingleTransaction(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("transaction_id") String transactionId) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Transaction transaction = null;

        //  If this user has permissions to read.
        if (AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            transaction = transactionService.requestTransactionDetails(transactionId);

            //  Make sure that the current OAuth user owns this transaction.
            if (!authCustomerOwnsAccount(auth, transaction.getAccountNumber())) {
                transaction = null;
                status = HttpStatus.BAD_REQUEST;
            }

        } else {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity(transaction, headers, status);
    }

    /**
     * Helper method that checks to see that the OAuth session user owns the 
     * requested resource before any processing occurs. 
     * 
     * @param auth the OAuth Athentication for a user's permissions
     * @param accountId the Accont ID of the Account we want to check Authorisation for
     * @return boolean returns true if the User is Authorised to view the resource
     */
    private boolean authCustomerOwnsAccount(OAuth2Authentication auth, String accountId) {
        boolean result = false;
        //  Get the current customer ID.
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
