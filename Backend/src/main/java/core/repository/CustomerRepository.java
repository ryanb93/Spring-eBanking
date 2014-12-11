package core.repository;

import core.domain.Customer;
import core.repository.custom.CustomCustomerRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>, CustomCustomerRepository {
}
