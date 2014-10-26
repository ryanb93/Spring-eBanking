package core.events.accounts;

import core.domain.Account;


public class RequestNewAccountEvent {

    private final Account account;

    public RequestNewAccountEvent(final Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }
    
}
