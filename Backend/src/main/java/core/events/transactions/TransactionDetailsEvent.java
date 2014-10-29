package core.events.transactions;

import core.domain.Transaction;
import core.events.ReadEvent;


public class TransactionDetailsEvent extends ReadEvent {
    
    private final Transaction transaction;
    
    public TransactionDetailsEvent(Transaction transaction) {
        this.transaction = transaction;
    }
    
    public Transaction getTransaction() {
        return this.transaction;
    }
}
