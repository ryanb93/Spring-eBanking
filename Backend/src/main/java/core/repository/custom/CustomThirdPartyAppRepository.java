package core.repository.custom;

import core.domain.ThirdPartyApp;
import java.util.List;


public interface CustomThirdPartyAppRepository {
    public List<ThirdPartyApp> findAllByCustomerId(String customerId);
}
