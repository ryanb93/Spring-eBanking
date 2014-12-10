package core.events.accounts;

import core.domain.Account;

/**
 * This is a class that is returned to a service when it has requested the
 * details of a single account.
 */
public class AccountDetailsEvent {

    private final Account account;

    public AccountDetailsEvent(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

}
