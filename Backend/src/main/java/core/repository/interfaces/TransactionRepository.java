package core.repository.interfaces;

import core.domain.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The Transaction Repository Interface
 */
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>, CustomTransactionRepository {
}
