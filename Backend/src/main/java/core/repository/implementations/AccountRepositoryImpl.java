package core.repository.implementations;

import core.domain.Account;
import core.repository.custom.CustomAccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class AccountRepositoryImpl implements CustomAccountRepository {

    private final MongoOperations operations;

    @Autowired
    public AccountRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }

    @Override
    public List<Account> findAllByCustomerId(String customerId) {
        Query findByCustomer = new Query();
        findByCustomer.addCriteria(Criteria.where("customerId").is(customerId));
        return operations.find(findByCustomer, Account.class);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        Query findByAccNum = new Query();
        findByAccNum.addCriteria(Criteria.where("accountNumber").is(accountNumber));
        Account found = operations.findOne(findByAccNum, Account.class);
        return found;
    }

}
