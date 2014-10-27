package core.repository.custom;

import core.domain.ThirdPartyApp;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomThirdPartyAppRepository {
    public List<ThirdPartyApp> findAllByCustomerId(String customerId);
}
