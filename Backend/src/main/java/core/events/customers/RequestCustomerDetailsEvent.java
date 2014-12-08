package core.events.customers;

/**
 * This is a request event that should be used to get the details
 * of a single customer back.
 */
public class RequestCustomerDetailsEvent {

    private final String customerId;
    
    public RequestCustomerDetailsEvent(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerId() {
        return this.customerId;
    }
    
}
