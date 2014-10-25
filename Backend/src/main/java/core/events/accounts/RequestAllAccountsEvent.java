package core.events.accounts;

import core.events.RequestReadEvent;

public class RequestAllAccountsEvent extends RequestReadEvent {
    
    private final String customerId;
    
    public RequestAllAccountsEvent(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerId() {
        return this.customerId;
    }
    
}
