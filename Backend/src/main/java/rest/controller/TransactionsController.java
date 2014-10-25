package rest.controller;

import core.domain.Transaction;
import core.services.TransactionEventHandler;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.TRANSACTIONS)
public class TransactionsController {

    @Autowired
    private TransactionEventHandler transactionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Transaction> getAllTransactions() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/singleTransaction")
    public Transaction getSingleTransaction() {
        Transaction transaction1 = new Transaction("Morrisons", "Customer 1", 45.79, new Date());
        return transaction1;
    }

}
