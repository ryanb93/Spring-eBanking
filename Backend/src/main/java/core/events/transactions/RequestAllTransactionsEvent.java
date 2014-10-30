package core.events.transactions;

import core.events.RequestReadEvent;

public class RequestAllTransactionsEvent extends RequestReadEvent {
  
    private final String accountId;
    private final int page;
    
    public RequestAllTransactionsEvent(String accountId, int page) {
        this.accountId = accountId;
        this.page = page;
    }
    
    public String getAccountId() {
        return this.accountId;
    }

    public int getPage() {
        return this.page;
    }
    
}
