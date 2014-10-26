package core.events.accounts;

import core.events.DeleteEvent;

public class DeleteAccountEvent extends DeleteEvent {

    private final String accountId;
    
    public DeleteAccountEvent(String accountId) {
        this.accountId = accountId;
    }
    
    public String getAccountId() {
        return this.accountId;
    }
    
}
