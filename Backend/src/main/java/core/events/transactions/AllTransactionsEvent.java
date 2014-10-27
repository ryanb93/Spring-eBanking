package core.events.transactions;

import core.domain.Transaction;
import core.events.ReadEvent;
import java.util.ArrayList;
import java.util.List;

public class AllTransactionsEvent extends ReadEvent{
    
    private final List<String> transactionIds;

    public AllTransactionsEvent(List<Transaction> transactions) {    
        this.transactionIds = new ArrayList();
        transactions.stream().forEach((t) -> {
            this.transactionIds.add(t.getAccountId());
        });
    }

    public List<String> getTransactionIds() {
      return this.transactionIds;
    }
    
}
