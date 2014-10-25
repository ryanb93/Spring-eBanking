package rest.controller;

import core.domain.ThirdPartyApp;
import core.services.ThirdPartyAppsEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.APP)
public class AppsController {

    @Autowired
    private ThirdPartyAppsEventHandler tpaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ThirdPartyApp> getAllApps() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = Routes.APP_ID, method = RequestMethod.GET)
    public ThirdPartyApp getThirdPartyApp(@PathVariable("app_id") int id) {
        return new ThirdPartyApp("All your monies", true, true);
    }

    @RequestMapping(value = Routes.APP_ID, method = RequestMethod.PUT)
    public ThirdPartyApp updateThirdPartyApp(@PathVariable("app_id") int id) {
        return new ThirdPartyApp("All your monies", true, true);
    }

    @RequestMapping("/singleApp")
    public ThirdPartyApp getSingleApp() {
        ThirdPartyApp application = new ThirdPartyApp("BalanceManager Plus", true, false);
        return application;
    }

}
