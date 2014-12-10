package core.repository.implementations;

import core.domain.Transaction;
import core.repository.custom.CustomTransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class TransactionRepositoryImpl implements CustomTransactionRepository {

    private final MongoOperations operations;

    @Autowired
    public TransactionRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }

    @Override
    public List<Transaction> findAllByAccountNumber(String accountNumber, int page) {
        Query findByAccount = new Query();
        findByAccount.addCriteria(Criteria.where("accountNumber").is(accountNumber));
        findByAccount.limit(10);
        findByAccount.skip(10 * page);
        findByAccount.with(new Sort(Sort.Direction.DESC, "date"));
        return operations.find(findByAccount, Transaction.class);
    }

}
