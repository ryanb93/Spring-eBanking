package core.services;

import core.events.customers.AllCustomersEvent;
import core.events.customers.CreateCustomerEvent;
import core.events.customers.CustomerDetailsEvent;
import core.events.customers.RequestAllCustomersEvent;
import core.events.customers.RequestCustomerDetailsEvent;
import core.events.customers.RequestNewCustomerEvent;

public interface CustomerService {    
    public CustomerDetailsEvent requestCustomerDetails(RequestCustomerDetailsEvent requestCustomerDetailsEvent);
    public AllCustomersEvent requestAllCustomers(RequestAllCustomersEvent requestAllCustomersEvent);
    public CreateCustomerEvent requestNewCustomer(RequestNewCustomerEvent requestNewCustomerEvent);
}
