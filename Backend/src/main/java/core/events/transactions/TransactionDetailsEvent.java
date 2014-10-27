package core.events.transactions;

import core.domain.Account;
import core.domain.Transaction;


public class TransactionDetailsEvent {
    
    private final Transaction transaction;
    
    public TransactionDetailsEvent(Transaction transaction) {
        this.transaction = transaction;
    }
    
    public Transaction getTransaction() {
        return this.transaction;
    }
}
