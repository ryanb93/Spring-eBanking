package core.services;

import core.events.thirdPartyApps.AllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestAllThirdPartyAppsEvent;
import core.repository.ThirdPartyAppsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ThirdPartyAppsEventHandler implements ThirdPartyAppsService {
    
    @Autowired
    private ThirdPartyAppsRepository thirdPartyAppsRepository;

    @Override
    public AllThirdPartyAppsEvent requestAllThirdPartyApps(RequestAllThirdPartyAppsEvent requestAllThirdPartyAppsEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
