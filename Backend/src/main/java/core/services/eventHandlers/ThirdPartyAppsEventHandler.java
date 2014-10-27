package core.services.eventHandlers;

import core.events.thirdPartyApps.AllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestAllThirdPartyAppsEvent;
import core.repository.ThirdPartyAppRepository;
import core.services.ThirdPartyAppsService;
import org.springframework.beans.factory.annotation.Autowired;

public class ThirdPartyAppsEventHandler implements ThirdPartyAppsService {
    
    @Autowired
    private ThirdPartyAppRepository thirdPartyAppsRepository;

    @Override
    public AllThirdPartyAppsEvent requestAllThirdPartyApps(RequestAllThirdPartyAppsEvent requestAllThirdPartyAppsEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
