package core.repository.interfaces;

import core.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The Account Repository Interface
 */
@Repository
public interface AccountRepositoryInterface extends MongoRepository<Account, String>, CustomAccountRepositoryInterface {
}
