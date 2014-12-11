package core.services;

import core.domain.Customer;
import core.repository.CustomerRepository;
import core.services.interfaces.CustomerServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class CustomerService implements CustomerServiceInterface {
    
    /** */
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 
     * @param customerId
     * @return Customer
     */
    @Override
    public Customer requestCustomerDetails(String customerId) {
        return customerRepository.findOne(customerId);
    }
    
    /**
     * 
     * @return List<Customer>
     */
    @Override
    public List<Customer> requestAllCustomers() {
        return customerRepository.findAll();
    }
    
    /**
     * 
     * @param customer
     * @return Customer
     */
    @Override
    public Customer requestNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * 
     * @param customer
     * @return Customer
     */
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

    /**
     * 
     * @param apiUserId
     * @return String of customer id
     */
    @Override
    public String requestCustomerId(String apiUserId) {
        return customerRepository.findByApiUserId(apiUserId).getCustomerId();
    }
    
    /**
     * Method which removes a Customer from MongoDB.
     * 
     * @param customerId - The ID of the Customer we want to remove..
     */
    @Override
    public void removeCustomer(String customerId){
        customerRepository.delete(customerRepository.findOne(customerId));
    }

}
