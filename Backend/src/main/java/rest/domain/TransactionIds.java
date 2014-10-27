package rest.domain;

import core.domain.Transaction;
import java.util.ArrayList;
import java.util.List;


public class TransactionIds {

    private final List<String> transactionIds;
    
    public TransactionIds(List<Transaction> transactions) {
        this.transactionIds = new ArrayList();
        for(Transaction transaction : transactions) {
            this.transactionIds.add(transaction.getTransactionId());
        }
    }
    
    public List<String> getTransactionIds() {
        return this.transactionIds;
    }
    
}
