package core.events.customers;

import core.events.RequestReadEvent;

/**
 * This is a request event that should be used to get the details
 * of a single customer back.
 */
public class RequestCustomerDetailsEvent extends RequestReadEvent {

    private final String customerId;
    
    public RequestCustomerDetailsEvent(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerId() {
        return this.customerId;
    }
    
}
