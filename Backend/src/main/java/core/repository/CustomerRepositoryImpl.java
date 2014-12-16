package core.repository;

import core.domain.Customer;
import core.repository.interfaces.CustomCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * The Implementation of CustomCustomerRepository, enabling custom DB Queries 
 */
@Repository
public class CustomerRepositoryImpl implements CustomCustomerRepository {
    
        
    /** 
     * MongoOperations is a MongoDB component that enables the development and use of custom DB queries
     */
    private final MongoOperations operations;
    
    /**
     * Constructor for CustomerRepositoryImpl
     * 
     * @param operations the MongoOperations variable. Needs to not be null.
     */
    @Autowired
    public CustomerRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }
    
    /**
     * Method to retrieve a Customer by its API User ID
     * 
     * @param apiUserId the API User ID of the Customer we want to retrieve
     * @return Customer the Customer with the API User ID specified
     */
    @Override
    public Customer findByApiUserId(String apiUserId) {
        Query findByApiUserId = new Query();
        findByApiUserId.addCriteria(Criteria.where("apiUserId").is(apiUserId));
        return operations.findOne(findByApiUserId, Customer.class);
    }
}
