package core.events.accounts;

import core.events.RequestReadEvent;

public class RequestAccountDetailsEvent extends RequestReadEvent {

    private final String accountId;
    
    public RequestAccountDetailsEvent(String accountId) {
        this.accountId = accountId;
    }
    
    public String getAccountId() {
        return this.accountId;
    }
    
}
