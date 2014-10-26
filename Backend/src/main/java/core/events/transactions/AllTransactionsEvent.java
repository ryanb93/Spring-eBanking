package core.events.transactions;

import core.domain.Transaction;
import core.events.ReadEvent;
import java.util.Collections;
import java.util.List;

public class AllTransactionsEvent extends ReadEvent{
    
    private final List<Transaction> transactions;

    public AllTransactionsEvent(List<Transaction> transactions) {
      this.transactions = Collections.unmodifiableList(transactions);
    }

    public List<Transaction> getTransactions() {
      return this.transactions;
    }
    
}
