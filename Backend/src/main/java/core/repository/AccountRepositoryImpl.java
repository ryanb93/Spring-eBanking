package core.repository;

import core.domain.Account;
import core.repository.interfaces.CustomAccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * The Implementation of CustomAccountRepository, enabling custom DB Queries 
 */
@Repository
public class AccountRepositoryImpl implements CustomAccountRepository {
    
    /** 
     *MongoOperations is a MongoDB component that enables the development and use of custom DB queries
     */
    private final MongoOperations operations;

    /**
     * Constructor for AccountRepositoryImpl
     * @param operations the MongoOperations variable. Needs to not be null.
     */
    @Autowired
    public AccountRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }
    
    /**
     * Method to retrieve all Accounts owned by a single Customer
     * @param customerId the ID of the Customer whose Accounts we want to retrieve
     * @return List<Account> a List of the Accounts owned by the Customer Specified 
     */
    @Override
    public List<Account> findAllByCustomerId(String customerId) {
        Query findByCustomer = new Query();
        findByCustomer.addCriteria(Criteria.where("customerId").is(customerId));
        return operations.find(findByCustomer, Account.class);
    }
    
    /**
     * Method to find an Account by its Account Number
     * @param accountNumber the Account Number of the Account we want to retrieve
     * @return Account the Account with the Account Number Specified
     */
    @Override
    public Account findByAccountNumber(String accountNumber) {
        Query findByAccNum = new Query();
        findByAccNum.addCriteria(Criteria.where("accountNumber").is(accountNumber));
        Account found = operations.findOne(findByAccNum, Account.class);
        return found;
    }

}
