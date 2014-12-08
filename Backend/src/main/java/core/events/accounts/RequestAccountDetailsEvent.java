package core.events.accounts;

public class RequestAccountDetailsEvent {

    private final String accountId;
    
    public RequestAccountDetailsEvent(String accountId) {
        this.accountId = accountId;
    }
    
    public String getAccountId() {
        return this.accountId;
    }
    
}
