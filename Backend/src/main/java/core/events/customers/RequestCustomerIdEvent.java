package core.events.customers;


public class RequestCustomerIdEvent {

    private final String apiUserId;
    
    public RequestCustomerIdEvent(String apiUserId) {
        this.apiUserId = apiUserId;
    }
    
    public String getApiUserId() {
        return this.apiUserId;
    }
    
}
