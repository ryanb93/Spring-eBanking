package core.services;

import core.events.thirdPartyApps.AllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestAllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestThirdPartyAppDetailsEvent;
import core.events.thirdPartyApps.ThirdPartyAppDetailsEvent;

public interface ThirdPartyAppsService {
    public AllThirdPartyAppsEvent requestAllThirdPartyApps(RequestAllThirdPartyAppsEvent requestAllThirdPartyAppsEvent);
    public ThirdPartyAppDetailsEvent requestThirdPartyAppDetails(RequestThirdPartyAppDetailsEvent requestThirdPartyAppDetailsEvent);
}
