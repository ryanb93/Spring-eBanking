package core.events.customers;

import core.domain.Customer;

public class RequestUpdateCustomerDetailsEvent {

    private final Customer customer;

    public RequestUpdateCustomerDetailsEvent(final Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

}
