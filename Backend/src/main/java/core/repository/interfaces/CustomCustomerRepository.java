package core.repository.interfaces;

import core.domain.Customer;
import org.springframework.stereotype.Repository;

/**
 * The Customer Repository interface for Custom Methods
 */
@Repository
public interface CustomCustomerRepository {

    /**
     * Method to retrieve a Customer by its API User ID
     * 
     * @param apiUserId the API User ID of the Customer we want to retrieve
     * @return Customer the Customer with the API User ID specified
     */
    public Customer findByApiUserId(String apiUserId);
}
