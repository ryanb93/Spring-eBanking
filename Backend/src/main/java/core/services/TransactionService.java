package core.services;

import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.CreateTransactionEvent;
import core.events.transactions.RequestAllTransactionsEvent;

public interface TransactionService {
    public CreateTransactionEvent requestNewTransaction(CreateTransactionEvent createTransactionEvent);
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent);
}
