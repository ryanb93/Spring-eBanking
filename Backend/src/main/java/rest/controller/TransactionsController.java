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
     * @param auth
     * @param accountNumber
     * @param request
     * @return ResponseEntity<List<Transaction>>
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
            status = HttpStatus.UNAUTHORIZED;
        }
        //  Return transactions to the user.
        return new ResponseEntity(transactions, headers, status);
    }

    /**
     * Method creates a new transaction for a given customers account. 
     * This method is executed when a POST request to this Route is received.
     * 
     * @param auth
     * @param accountNumber
     * @param transaction
     * @return ResponseEntity<Transaction>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transaction> createNewTransaction(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("account_number") String accountNumber,
            @RequestBody Transaction transaction) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;
        Transaction newTransaction = null;

        //  If this user has permissions to write.
        if (AuthHelper.CAN_WRITE_FROM_AUTH(auth)) {
            //  If this user's customer owns the account given.
            if (authCustomerOwnsAccount(auth, accountNumber)) {
                if(transaction.getValue() >= 0)  {
                //  Set the transaction account ID to the path variable.
                transaction.setAccountNumber(accountNumber);
                //  Set the date to current date.
                transaction.setDate(new Date());

                //  Get the transaction object back from the database.
                newTransaction = transactionService.requestNewTransaction(transaction);
                //  Make sure it exists.
                if (newTransaction == null) {
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                }
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
            } else {
                status = HttpStatus.BAD_REQUEST;
                        }
            
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity(newTransaction, headers, status);
    }

    /**
     * This method returns a single transaction for a given customers account. 
     * When the Routes.TRANSACTIONS_ID receives a GET request this method is called
     * 
     * @param auth
     * @param transactionId
     * @return ResponseEntity<Transaction>
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
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity(transaction, headers, status);
    }

    /**
     * Helper method that checks to see that the OAuth session user owns the 
     * requested resource before any processing occurs. 
     * 
     * @param auth
     * @param accountId
     * @return boolean
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
