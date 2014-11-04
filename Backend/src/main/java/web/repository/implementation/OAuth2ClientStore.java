package web.repository.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
//import web.repository.OAuth2ClientRepository;


//@Repository
//public class OAuth2ClientStore implements OAuth2ClientRepository {
//
//    private final MongoOperations operations;
//    
//    private List<ClientDetails> details = new ArrayList(); 
//
//    @Autowired
//    public OAuth2ClientStore(MongoOperations operations) {
//      Assert.notNull(operations, "MongoOperations must not be null!");
//      this.operations = operations;
//      
//      ClientDetails test = new ClientDetails("");
//      test.setId("test");
//      
//      
//    }
//    
//    @Override
//    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//        Query findByAccount = new Query();
//        findByAccount.addCriteria(Criteria.where("clientId").is(clientId));
//        return operations.findOne(findByAccount, ClientDetails.class);  
//    }
//
//}
