package core.services.eventHandlers;

import core.domain.Customer;
import core.events.customers.AllCustomersEvent;
import core.events.customers.CreateCustomerEvent;
import core.events.customers.CustomerDetailsEvent;
import core.events.customers.RequestAllCustomersEvent;
import core.events.customers.RequestCustomerDetailsEvent;
import core.events.customers.RequestNewCustomerEvent;
import core.events.customers.RequestUpdateCustomerDetailsEvent;
import core.events.customers.UpdateCustomerDetailsEvent;
import core.repository.CustomerRepository;
import core.services.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerEventHandler implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDetailsEvent requestCustomerDetails(RequestCustomerDetailsEvent requestCustomerDetailsEvent) {
        String id = requestCustomerDetailsEvent.getCustomerId();
        Customer customer = customerRepository.findOne(id);
        return new CustomerDetailsEvent(customer);
    }
    
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
    
    @Override
    public UpdateCustomerDetailsEvent requestUpdateCustomer(RequestUpdateCustomerDetailsEvent requestUpdateCustomerDetailsEvent) {
        Customer newCustomer = requestUpdateCustomerDetailsEvent.getCustomer();
        
        String existingId = newCustomer.getCustomerId();
        
        Customer existingCustomer = customerRepository.findOne(existingId);
        
        if(existingCustomer == null) {
            //Customer was not found!
            return null;
        }
        
        Customer savedCustomer = customerRepository.save(newCustomer);
               
        return new UpdateCustomerDetailsEvent(savedCustomer);
    }

}
