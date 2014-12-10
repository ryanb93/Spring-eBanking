package core.repository;

import core.domain.Account;
import core.repository.custom.CustomAccountRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String>, CustomAccountRepository {
}
