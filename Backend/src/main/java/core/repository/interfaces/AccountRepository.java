package core.repository.interfaces;

import core.domain.Account;
import core.repository.interfaces.CustomAccountRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The Account Repository Interface
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String>, CustomAccountRepository {
}
