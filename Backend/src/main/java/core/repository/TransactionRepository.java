package core.repository;

import core.domain.Transaction;
import core.repository.custom.CustomTransactionRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>, CustomTransactionRepository {
}
