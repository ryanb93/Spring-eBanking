package core.services;

import core.events.thirdPartyApps.AllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestAllThirdPartyAppsEvent;

public interface ThirdPartyAppsService {
    public AllThirdPartyAppsEvent requestAllThirdPartyApps(RequestAllThirdPartyAppsEvent requestAllThirdPartyAppsEvent);
}
