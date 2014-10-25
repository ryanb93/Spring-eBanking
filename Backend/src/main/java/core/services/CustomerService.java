package core.services;

import core.events.customers.AllCustomersEvent;
import core.events.customers.CreateCustomerEvent;
import core.events.customers.RequestAllCustomersEvent;
import core.events.customers.RequestNewCustomerEvent;

public interface CustomerService {    
    public AllCustomersEvent requestAllCustomers(RequestAllCustomersEvent requestAllCustomersEvent);
    public CreateCustomerEvent requestNewCustomer(RequestNewCustomerEvent requestNewCustomerEvent);
}
