package core.events.customers;

import core.domain.Customer;

public class RequestNewCustomerEvent {

    private final Customer customer;

    public RequestNewCustomerEvent(final Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

}
