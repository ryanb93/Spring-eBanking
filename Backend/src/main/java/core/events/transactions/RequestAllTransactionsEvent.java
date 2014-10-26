package core.events.transactions;

import core.events.RequestReadEvent;

public class RequestAllTransactionsEvent extends RequestReadEvent {
  
    private final String accountId;
    
    public RequestAllTransactionsEvent(String accountId) {
        this.accountId = accountId;
    }
    
    public String getAccountId() {
        return this.accountId;
    }
    
}
