package core.services;

import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.RequestAllTransactionsEvent;

public interface TransactionService {
    
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent);
}
