package core.events.thirdPartyApps;

import core.domain.ThirdPartyApp;

public class ThirdPartyAppDetailsEvent {

    private final ThirdPartyApp app;
    
    public ThirdPartyAppDetailsEvent(ThirdPartyApp app) {
        this.app = app;
    }
    
    public ThirdPartyApp getThirdPartyApp() {
        return this.app;
    }
    
}
