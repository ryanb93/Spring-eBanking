package core.events.customers;

import core.domain.Customer;


public class UpdateCustomerDetailsEvent {

    private final Customer updatedCustomer;

    public UpdateCustomerDetailsEvent(final Customer updatedCustomer) {
        this.updatedCustomer = updatedCustomer;
    }

    public Customer getUpdatedCustomer() {
        return this.updatedCustomer;
    }
    
}
