package core.services.interfaces;

import core.domain.Customer;
import java.util.List;

/**
 * Interface for the Customer Service.
 * This service acts as a layer between the view controllers and the database
 * repository.
 */
public interface CustomerServiceInterface {
    
    /**
     * Method which returns the details of a single Customer based on its
     * database ID.
     * 
     * @param customerId the ID of the Customer we want to retrieve
     * @return Customer the Customer with the ID specified
     */
    public Customer requestCustomerDetails(String customerId);
    
    /**
     * Method to retrieve all Customers stored in MongoDB
     * 
     * @return List<Customer> a list of all Customers stored in MongoDB
     */
    public List<Customer> requestAllCustomers();
    
    /**
     * Method to save a new Customer to MongoDB
     * 
     * @param customer the Customer we want to save to MongoDB
     * @return Customer the newly saved Customer
     */
    public Customer requestNewCustomer(Customer customer);
    
    /**
     * Method to update a Customer saved in MongoDB
     * 
     * @param customer the Customer we want to update
     * @return Customer the newly updated Customer
     */
    public Customer requestUpdateCustomer(Customer customer);
    
    /**
     * Method to retrieve the Customer ID of the Customer linked to an API User
     * 
     * @param apiUserId the API User ID that we want to search for
     * @return String the Customer ID that possesses the API User ID we specify
     */
    public String requestCustomerId(String apiUserId);
    
    /**
     * Method which removes a Customer from MongoDB.
     * 
     * @param customerId - The ID of the Customer we want to remove..
     */
    public void requestRemoveCustomer(String customerId);
}
