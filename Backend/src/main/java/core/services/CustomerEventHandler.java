/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class CustomerEventHandler {
    
    @Autowired
    private CustomerRepository customerRepository;

    public AllCustomersEvent requestAllCustomers(RequestAllCustomersEvent requestAllCustomersEvent) {
        List<Customer> customers = customerRepository.findAll();
        return new AllCustomersEvent(customers);
    }
    
    public CreateCustomerEvent requestNewCustomer(RequestNewCustomerEvent requestNewCustomerEvent) {
        Customer newCustomer = requestNewCustomerEvent.getCustomer();
        customerRepository.save(newCustomer);
        return new CreateCustomerEvent(customerRepository.findOne(newCustomer.getCustomerId()));
    }
}
