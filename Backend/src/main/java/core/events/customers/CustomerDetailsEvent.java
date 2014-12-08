package core.events.customers;

import core.domain.Customer;

public class CustomerDetailsEvent {
    
    private final Customer customer;
    
    public CustomerDetailsEvent(Customer customer) {
        this.customer = customer;
    }
    
    public Customer getCustomer() {
        return this.customer;
    }

}
