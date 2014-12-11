package core.repository.implementations;

import core.domain.Customer;
import core.repository.custom.CustomCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 
 */
@Repository
public class CustomerRepositoryImpl implements CustomCustomerRepository {
    
    /** */
    private final MongoOperations operations;
    
    /**
     * 
     * @param operations 
     */
    @Autowired
    public CustomerRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }
    
    /**
     * 
     * @param apiUserId
     * @return Customer
     */
    @Override
    public Customer findByApiUserId(String apiUserId) {
        Query findByApiUserId = new Query();
        findByApiUserId.addCriteria(Criteria.where("apiUserId").is(apiUserId));
        return operations.findOne(findByApiUserId, Customer.class);
    }
}
