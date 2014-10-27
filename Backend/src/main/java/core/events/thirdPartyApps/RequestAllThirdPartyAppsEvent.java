package core.events.thirdPartyApps;

import core.events.RequestReadEvent;

public class RequestAllThirdPartyAppsEvent extends RequestReadEvent {

    private final String customerId;
    
    public RequestAllThirdPartyAppsEvent(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerId() {
        return this.customerId;
    }
    
}
