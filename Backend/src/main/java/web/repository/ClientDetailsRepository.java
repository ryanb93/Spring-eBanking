package web.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends MongoRepository<ClientDetails, String> {

}