package core.repository;

import core.domain.ThirdPartyApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyAppRepository extends MongoRepository<ThirdPartyApp, String> {
}
