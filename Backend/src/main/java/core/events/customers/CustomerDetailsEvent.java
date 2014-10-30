package core.events.customers;

import core.domain.Customer;
import core.events.ReadEvent;


public class CustomerDetailsEvent extends ReadEvent {
    
    private final Customer customer;
    
    public CustomerDetailsEvent(Customer customer) {
        this.customer = customer;
    }
    
    public Customer getCustomer() {
        return this.customer;
    }

}
