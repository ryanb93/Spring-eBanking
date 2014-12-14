package core.services;

import core.domain.Customer;
import core.exceptions.APIException;
import core.repository.interfaces.CustomerRepository;
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
    private CustomerRepository customerRepository;

    /**
     * Method which returns the details of a single Customer based on its
     * database ID.
     * 
     * @param customerId the ID of the Customer we want to retrieve
     * @return Customer the Customer with the ID specified
     * @throws core.exceptions.APIException
     */
    @Override
    public Customer requestCustomerDetails(String customerId) throws APIException {
        Customer customer = customerRepository.findOne(customerId);
        if (customer == null) throw new APIException("Customer does not exist.");
        return customer;
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
     * @throws core.exceptions.APIException
     */
    @Override
    public Customer requestNewCustomer(Customer customer) throws APIException {
        if(customer == null) throw new APIException("No customer supplied.");
        Customer saved = customerRepository.save(customer);
        if(saved == null) throw new APIException("Could not save customer.");
        return saved;
    }

    /**
     * Method to update a Customer saved in MongoDB
     * 
     * @param customer the Customer we want to update
     * @return Customer the newly updated Customer
     * @throws core.exceptions.APIException
     */
    @Override
    public Customer requestUpdateCustomer(Customer customer) throws APIException {
        //Get the ID of the customer.
        String existingId = customer.getCustomerId();
        //Get the existing customer from the repo.
        Customer existingCustomer = customerRepository.findOne(existingId);
        //If the customer does not exit in the repo.
        if (existingCustomer == null) throw new APIException("Customer does not exist.");
        //Attempt to save the customer.
        Customer saved = customerRepository.save(customer);
        //If if could not be saved.
        if (saved == null) throw new APIException("Customer could not be saved.");
        return saved;
    }

    /**
     * Method to retrieve the Customer ID of the Customer linked to an API User
     * 
     * @param apiUserId the API User ID that we want to search for
     * @return String the Customer ID that possesses the API User ID we specify
     * @throws core.exceptions.APIException
     */
    @Override
    public String requestCustomerId(String apiUserId) throws APIException {
        //Find the customer in the repo.
        Customer customer = customerRepository.findByApiUserId(apiUserId);
        //If the customer could not be found.
        if(customer == null) throw new APIException("Could not find customer.");
        //Get the ID of the customer.
        String customerID = customer.getCustomerId();
        //If there is no ID.
        if(customerID == null) throw new APIException("Customer has no ID.");
        return customerID;
    }
    
    /**
     * Method which removes a Customer from MongoDB.
     * 
     * @param customerId - The ID of the Customer we want to remove.
     * @throws core.exceptions.APIException
     */
    @Override
    public void requestRemoveCustomer(String customerId) throws APIException {
        //Get the customer from the database.
        Customer customer = customerRepository.findOne(customerId);
        //If customer does not exist throw exception.
        if(customer == null) throw new APIException("Could not find customer.");
        //Try and delete the customer from the repo.
        customerRepository.delete(customer);
        //Check to see if the customer exists, should be null.
        Customer deleted = customerRepository.findOne(customerId);
        //If customer still exists we failed to delete the customer.
        if(deleted != null) throw new APIException("Could not deleted customer.");
    }

}
