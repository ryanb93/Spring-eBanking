package core.events.thirdPartyApps;


public class RequestThirdPartyAppDetailsEvent {

    private final String appId;
    
    public RequestThirdPartyAppDetailsEvent(String appId) {
        this.appId = appId;
    }
    
    public String getAppId() {
        return this.appId;
    }
    
}
