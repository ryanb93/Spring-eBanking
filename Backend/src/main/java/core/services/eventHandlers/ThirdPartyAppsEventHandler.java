package core.services.eventHandlers;

import core.domain.ThirdPartyApp;
import core.events.thirdPartyApps.AllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestAllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestThirdPartyAppDetailsEvent;
import core.events.thirdPartyApps.ThirdPartyAppDetailsEvent;
import core.repository.ThirdPartyAppRepository;
import core.services.ThirdPartyAppsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ThirdPartyAppsEventHandler implements ThirdPartyAppsService {
    
    @Autowired
    private ThirdPartyAppRepository thirdPartyAppsRepository;

    @Override
    public AllThirdPartyAppsEvent requestAllThirdPartyApps(RequestAllThirdPartyAppsEvent requestAllThirdPartyAppsEvent) {
        String customerId = requestAllThirdPartyAppsEvent.getCustomerId();
        List<ThirdPartyApp> apps = thirdPartyAppsRepository.findAllByCustomerId(customerId);
        return new AllThirdPartyAppsEvent(apps);
    }
    
    @Override
    public ThirdPartyAppDetailsEvent requestThirdPartyAppDetails(RequestThirdPartyAppDetailsEvent requestThirdPartyAppDetailsEvent) {
        String appId = requestThirdPartyAppDetailsEvent.getAppId();
        ThirdPartyApp app = thirdPartyAppsRepository.findOne(appId);
        return new ThirdPartyAppDetailsEvent(app);
    }
}