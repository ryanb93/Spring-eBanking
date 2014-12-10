package rest.controller;

import components.AuthHelper;
import core.domain.Transaction;
import core.events.transactions.CreateTransactionEvent;
import core.events.transactions.RequestAllTransactionsEvent;
import core.events.transactions.RequestTransactionDetailsEvent;
import core.events.transactions.TransactionDetailsEvent;
import core.services.TransactionService;
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
import core.events.accounts.AccountDetailsEvent;
import core.events.accounts.RequestAccountDetailsFromNumberEvent;
import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.RequestCreateTransactionEvent;
import core.services.AccountService;
import core.services.CustomerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping(Routes.TRANSACTIONS)
@Secured("ROLE_USER")
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    
    /**
     * 
     * @param auth
     * @param accountNumber
     * @param request
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getAllTransactions(@AuthenticationPrincipal OAuth2Authentication auth, 
                                                                @PathVariable("account_number") String accountNumber,
                                                                HttpServletRequest request) {
        
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Transaction> transactions = null;
        
        //If this user has permissions to read.
        if(AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            //Get the page parameter.
            String pageParam = request.getParameter("page");
            //If it is not found then use the value 0.
            if(pageParam == null) pageParam = "0";
            //Set page to 0.
            int pageInt = 0;
            //Attempt to convert the intrusted input from the URL into an int.
            try { 
                pageInt = Integer.parseInt(pageParam); 
            }
            catch(NumberFormatException e) {
                //If the value given was not an int then return bad request.
                return new ResponseEntity(null, headers, HttpStatus.BAD_REQUEST);
            }
            //Request the transactions using the account ID and the page value.
            RequestAllTransactionsEvent requestAll = new RequestAllTransactionsEvent(accountNumber, pageInt);
            //Send the event to the service.
            AllTransactionsEvent all = transactionService.requestAllTransactions(requestAll);
            //Extract the list from the response.
            transactions = all.getTransactions();
        }
        else {
            status = HttpStatus.UNAUTHORIZED;
        }
        //Return transactions to the user.
        return new ResponseEntity(transactions, headers, status);
    }
    
    /**
     * 
     * @param auth
     * @param accountNumber
     * @param transaction
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transaction> createNewTransaction(@AuthenticationPrincipal OAuth2Authentication auth,
                                                            @PathVariable("account_number") String accountNumber,
                                                            @RequestBody Transaction transaction) {
        
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;
        Transaction newTransaction = null;
        
        //If this user has permissions to write.
        if(AuthHelper.CAN_WRITE_FROM_AUTH(auth)) {        
            //If this user's customer owns the account given.
            if(authCustomerOwnsAccount(auth, accountNumber)) {
                //Set the transaction account ID to the path variable.
                transaction.setAccountNumber(accountNumber);
                //Set the date to current date.
                transaction.setDate(new Date());
                
                //Create the transaction in the database.
                RequestCreateTransactionEvent request = new RequestCreateTransactionEvent(transaction);
                CreateTransactionEvent event = transactionService.requestNewTransaction(request);
                
                //Get the transaction object back from the database.
                newTransaction = event.getTransaction();
                //Make sure it exists.
                if(newTransaction == null) {
                    status = HttpStatus.INTERNAL_SERVER_ERROR;
                }
            }
            else {
                status = HttpStatus.BAD_REQUEST;
            }
        }
        else {
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity(newTransaction, headers, status);
    }
    
    /**
     * 
     * @param auth
     * @param transactionId
     * @return 
     */
    @RequestMapping(value = Routes.TRANSACTIONS_ID, method = RequestMethod.GET)
    public ResponseEntity<Transaction> getSingleTransaction(@AuthenticationPrincipal OAuth2Authentication auth,
                                                            @PathVariable("transaction_id") String transactionId) {
        
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Transaction transaction = null;
        
        //If this user has permissions to read.
        if(AuthHelper.CAN_READ_FROM_AUTH(auth)) {  
            RequestTransactionDetailsEvent request = new RequestTransactionDetailsEvent(transactionId);
            TransactionDetailsEvent event = transactionService.requestTransactionDetails(request);
            transaction = event.getTransaction();
            
            //Make sure that the current OAuth user owns this transaction.
            if(!authCustomerOwnsAccount(auth, transaction.getAccountNumber())) {
                transaction = null;
                status = HttpStatus.BAD_REQUEST;
            }
            
        }
        else {
            status = HttpStatus.UNAUTHORIZED;
        }
        
        return new ResponseEntity(transaction, headers, status);
    }
    
    
       static Logger log = Logger.getLogger(TransactionsController.class.getName());

    /**
     * 
     * @param auth
     * @param accountId
     * @return 
     */
    private boolean authCustomerOwnsAccount(OAuth2Authentication auth, String accountId) {
        boolean result = false;
        //Get the current customer ID.
        String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);  
        
        log.log(Level.INFO, "customerId: {0}", customerId);
        log.log(Level.INFO, "accountId: {0}", accountId);
        
        RequestAccountDetailsFromNumberEvent details = new RequestAccountDetailsFromNumberEvent(accountId);
        
        AccountDetailsEvent accountEvent = accountService.requestAccountDetailsFromNumber(details);
        
        log.log(Level.INFO, "accountEvent: {0}", accountEvent);

        Account account =  accountEvent.getAccount();
        
        log.log(Level.INFO, "account: {0}", account);

        if(account != null) {                             
            log.log(Level.INFO, "getCustomerId: {0}", account.getCustomerId());
            if(account.getCustomerId().equalsIgnoreCase(customerId)) {
                result = true;
            }
        }
        return result;
    }
    
}
