package core.repository.interfaces;

import core.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The Customer Repository Interface
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>, CustomCustomerRepository {
}
