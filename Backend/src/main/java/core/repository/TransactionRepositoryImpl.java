package core.repository;

import core.domain.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<Transaction> findAllByAccountId(String accountId) {
      Query findByAccount = new Query();
      findByAccount.addCriteria(Criteria.where("accountId").is(accountId));
      return operations.find(findByAccount, Transaction.class);      
  }

}
