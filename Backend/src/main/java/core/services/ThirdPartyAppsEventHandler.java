package core.services;

import core.events.thirdPartyApps.AllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestAllThirdPartyAppsEvent;
import core.repository.ThirdPartyAppsRepository;

public class ThirdPartyAppsEventHandler implements ThirdPartyAppsService{
    
        private final ThirdPartyAppsRepository thirdPartyAppsRepository;

    public ThirdPartyAppsEventHandler(final ThirdPartyAppsRepository thirdPartyAppsRepository) {
        this.thirdPartyAppsRepository = thirdPartyAppsRepository;
    }

    @Override
    public AllThirdPartyAppsEvent requestAllThirdPartyApps(RequestAllThirdPartyAppsEvent requestAllThirdPartyAppsEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
