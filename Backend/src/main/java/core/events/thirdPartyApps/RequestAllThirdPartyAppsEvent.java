package core.events.thirdPartyApps;

public class RequestAllThirdPartyAppsEvent {

    private final String customerId;
    
    public RequestAllThirdPartyAppsEvent(String customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerId() {
        return this.customerId;
    }
    
}
