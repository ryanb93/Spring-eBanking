package core.repository.custom;

import core.domain.Customer;
import org.springframework.stereotype.Repository;

/**
 * 
 */
@Repository
public interface CustomCustomerRepository {

    /**
     * 
     * @param apiUserId
     * @return Customer
     */
    public Customer findByApiUserId(String apiUserId);
}
