package core.services;

import core.domain.Customer;
import core.repository.interfaces.CustomerRepositoryInterface;
import core.services.interfaces.CustomerServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the CustomerService, which holds methods that may access
 * the MongoDB Database to manipulate Customers
 */
@Service
public class CustomerService implements CustomerServiceInterface {
    
    /**
     * The Customer Repository, required to save and retrieve Customers
     */
    @Autowired
    private CustomerRepositoryInterface customerRepository;

    /**
     * Method which returns the details of a single Customer based on its
     * database ID.
     * 
     * @param customerId the ID of the Customer we want to retrieve
     * @return Customer the Customer with the ID specified
     */
    @Override
    public Customer requestCustomerDetails(String customerId) {
        return customerRepository.findOne(customerId);
    }
    
    /**
     * Method to retrieve all Customers stored in MongoDB
     * 
     * @return List<Customer> a list of all Customers stored in MongoDB
     */
    @Override
    public List<Customer> requestAllCustomers() {
        return customerRepository.findAll();
    }
    
    /**
     * Method to save a new Customer to MongoDB
     * 
     * @param customer the Customer we want to save to MongoDB
     * @return Customer the newly saved Customer
     */
    @Override
    public Customer requestNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Method to update a Customer saved in MongoDB
     * 
     * @param customer the Customer we want to update
     * @return Customer the newly updated Customer
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
     * Method to retrieve the Customer ID of the Customer linked to an API User
     * 
     * @param apiUserId the API User ID that we want to search for
     * @return String the Customer ID that possesses the API User ID we specify
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
    public void requestRemoveCustomer(String customerId){
        customerRepository.delete(customerRepository.findOne(customerId));
    }

}
