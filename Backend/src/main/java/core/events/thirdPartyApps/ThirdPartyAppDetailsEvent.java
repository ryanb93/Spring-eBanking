package core.events.thirdPartyApps;

import core.domain.ThirdPartyApp;
import core.events.ReadEvent;


public class ThirdPartyAppDetailsEvent extends ReadEvent {

    private final ThirdPartyApp app;
    
    public ThirdPartyAppDetailsEvent(ThirdPartyApp app) {
        this.app = app;
    }
    
    public ThirdPartyApp getThirdPartyApp() {
        return this.app;
    }
    
}
