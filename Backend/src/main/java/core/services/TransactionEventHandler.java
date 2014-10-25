package core.services;

import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.RequestAllTransactionsEvent;
import core.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionEventHandler implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
