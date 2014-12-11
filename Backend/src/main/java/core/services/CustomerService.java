package core.services;

import core.domain.Customer;
import core.repository.CustomerRepository;
import core.services.interfaces.CustomerServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer requestCustomerDetails(String customerId) {
        return customerRepository.findOne(customerId);
    }

    @Override
    public List<Customer> requestAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer requestNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer requestUpdateCustomer(Customer customer) {

        String existingId = customer.getCustomerId();

        Customer existingCustomer = customerRepository.findOne(existingId);

        if (existingCustomer == null) {
            //Customer was not found!
            return null;
        }

        return customerRepository.save(customer);
    }

    @Override
    public String requestCustomerId(String apiUserId) {
        return customerRepository.findByApiUserId(apiUserId).getCustomerId();
    }

}
