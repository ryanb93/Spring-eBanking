package core.repository;

import core.domain.Transaction;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    public List<Transaction> findAllByAccountId(String accountId);
}