package rest.controller;

import core.domain.ThirdPartyApp;
import core.events.thirdPartyApps.AllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestAllThirdPartyAppsEvent;
import core.events.thirdPartyApps.RequestThirdPartyAppDetailsEvent;
import core.events.thirdPartyApps.ThirdPartyAppDetailsEvent;
import core.services.ThirdPartyAppsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.THIRD_PARTY_APP)
@Secured("ROLE_USER")
public class ThirdPartyAppsController {

    @Autowired
    private ThirdPartyAppsService tpaService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<ThirdPartyApp> getAllTransactions(@PathVariable("customer_id") String customerId) {
        RequestAllThirdPartyAppsEvent request = new RequestAllThirdPartyAppsEvent(customerId);
        AllThirdPartyAppsEvent event = tpaService.requestAllThirdPartyApps(request);
        return event.getThirdPartyApps();
    }

    @RequestMapping(value = Routes.THIRD_PARTY_APP_ID, method = RequestMethod.GET)
    public ThirdPartyApp getThirdPartyApp(@PathVariable("app_id") String id) {
        RequestThirdPartyAppDetailsEvent request = new RequestThirdPartyAppDetailsEvent(id);
        ThirdPartyAppDetailsEvent event = tpaService.requestThirdPartyAppDetails(request);
        return event.getThirdPartyApp();
    }
    
    /**
     * TODO: This will probably be handled by our OAuth implementation in future.
     */
    @RequestMapping(value = Routes.THIRD_PARTY_APP_ID, method = RequestMethod.PUT)
    public ThirdPartyApp updateThirdPartyApp(@PathVariable("app_id") int id) {
        return null;
    }
}
