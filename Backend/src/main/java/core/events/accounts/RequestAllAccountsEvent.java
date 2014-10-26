package core.events.accounts;

import core.events.RequestReadEvent;

/**
 * This is a method which a service should use to request all the 
 * accounts for a single Customer. It should provide the CustomerID 
 * to get the accounts for.
 */
public class RequestAllAccountsEvent extends RequestReadEvent {
    
    private final String customerId;
    
    public RequestAllAccountsEvent(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerId() {
        return this.customerId;
    }
    
}
