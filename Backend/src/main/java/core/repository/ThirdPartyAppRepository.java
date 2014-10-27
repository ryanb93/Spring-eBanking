package core.repository;

import core.domain.ThirdPartyApp;
import core.repository.custom.CustomThirdPartyAppRepository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyAppRepository extends MongoRepository<ThirdPartyApp, String>, CustomThirdPartyAppRepository {
}
