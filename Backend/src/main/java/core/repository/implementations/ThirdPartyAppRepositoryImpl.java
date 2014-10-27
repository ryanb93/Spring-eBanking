package core.repository.implementations;

import core.domain.ThirdPartyApp;
import core.repository.custom.CustomThirdPartyAppRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;


public class ThirdPartyAppRepositoryImpl implements CustomThirdPartyAppRepository {
    
    private final MongoOperations operations;

    @Autowired
    public ThirdPartyAppRepositoryImpl(MongoOperations operations) {
      Assert.notNull(operations, "MongoOperations must not be null!");
      this.operations = operations;
    }

    @Override
    public List<ThirdPartyApp> findAllByCustomerId(String customerId) {
        Query findByAccount = new Query();
        findByAccount.addCriteria(Criteria.where("customerId").is(customerId));
        return operations.find(findByAccount, ThirdPartyApp.class);      
    }
}
