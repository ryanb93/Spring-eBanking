package core.repository;

import core.domain.Account;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends MongoRepository<Account, String> { 
    public List<Account> findAllByCustomerId(String customerId);
}