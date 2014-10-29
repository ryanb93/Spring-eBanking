package core.events.thirdPartyApps;

import core.events.RequestReadEvent;


public class RequestThirdPartyAppDetailsEvent extends RequestReadEvent {

    private final String appId;
    
    public RequestThirdPartyAppDetailsEvent(String appId) {
        this.appId = appId;
    }
    
    public String getAppId() {
        return this.appId;
    }
    
}
