package core.repository;

import core.domain.Transaction;
import core.repository.interfaces.CustomTransactionRepositoryInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * The Implementation of CustomTransactionRepositoryInterface, enabling custom DB Queries 
 */
@Repository
public class TransactionRepository implements CustomTransactionRepositoryInterface {
    
    /** 
     *MongoOperations is a MongoDB component that enables the development and use of custom DB queries
     */
    private final MongoOperations operations;
    
    /**
     * Constructor for TransactionRepositoryImpl
     * @param operations the MongoOperations variable. Needs to not be null.
     */
    @Autowired
    public TransactionRepository(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }
    
    /**
     * Method to find all Transactions owned by a specific Account
     * @param accountNumber the Account Number of the Account whose Transactions we want to retrieve
     * @param page the page number for displaying the Transactions
     * @return List<Transaction> a List of the Transactions owned by the specified AccountNumber
     */
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
