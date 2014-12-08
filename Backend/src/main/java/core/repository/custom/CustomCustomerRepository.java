package core.repository.custom;

import core.domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomCustomerRepository {
    public Customer findByApiUserId(String apiUserId);
}
