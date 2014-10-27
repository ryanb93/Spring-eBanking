package rest.controller;

import core.domain.Transaction;
import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.CreateTransactionEvent;
import core.events.transactions.RequestAllTransactionsEvent;
import core.events.transactions.RequestTransactionDetailsEvent;
import core.events.transactions.TransactionDetailsEvent;
import core.services.TransactionService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.TRANSACTIONS)
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;
    
    @RequestMapping(method = RequestMethod.GET)
    public AllTransactionsEvent getAllTransactions(@PathVariable("account_id") String accountId) {
        RequestAllTransactionsEvent request = new RequestAllTransactionsEvent(accountId);
        AllTransactionsEvent event = transactionService.requestAllTransactions(request);
        return event;
    }
    
    /*
     * TODO: I don't think we want this API exposed to the public.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Transaction> createNewTransaction(@PathVariable("account_id") String accountId, @RequestBody Transaction transaction, UriComponentsBuilder builder) {

        //TODO: There is some security risk here. Need to discuss.
        //Set the transaction account ID to the path variable.
        transaction.setAccountId(accountId);
        
        CreateTransactionEvent event = transactionService.requestNewTransaction(new CreateTransactionEvent(transaction));

        Transaction newTransaction = event.getTransaction();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(Routes.API)
                .buildAndExpand(newTransaction.getTransactionId()).toUri());

        return new ResponseEntity(newTransaction, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = Routes.TRANSACTIONS_ID, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Transaction getSingleTransaction(@PathVariable("transaction_id") String accountId) {
        RequestTransactionDetailsEvent request = new RequestTransactionDetailsEvent(accountId);
        TransactionDetailsEvent event = transactionService.requestTransactionDetails(request);
        return event.getTransaction();
    }
    
    @RequestMapping(Routes.TEST_SINGLE_TRANSACTION)
    public Transaction getSingleTransaction() {
        Transaction transaction1 = new Transaction("Morrisons", "Customer 1", 45.79, new Date());
        return transaction1;
    }

}
