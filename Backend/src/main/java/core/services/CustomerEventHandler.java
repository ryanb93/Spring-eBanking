package core.services;

import core.domain.Customer;
import core.events.customers.AllCustomersEvent;
import core.events.customers.CreateCustomerEvent;
import core.events.customers.RequestAllCustomersEvent;
import core.events.customers.RequestNewCustomerEvent;
import core.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerEventHandler implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AllCustomersEvent requestAllCustomers(RequestAllCustomersEvent requestAllCustomersEvent) {
        List<Customer> customers = customerRepository.findAll();
        return new AllCustomersEvent(customers);
    }
    
    @Override
    public CreateCustomerEvent requestNewCustomer(RequestNewCustomerEvent requestNewCustomerEvent) {
        Customer newCustomer = requestNewCustomerEvent.getCustomer();
        customerRepository.save(newCustomer);
        return new CreateCustomerEvent(customerRepository.findOne(newCustomer.getCustomerId()));
    }
}
