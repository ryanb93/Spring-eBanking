package core.services;

import core.events.accounts.AllAccountsEvent;
import core.events.accounts.RequestAllAccountsEvent;

/**
 * Class is used to core things 
 */
public interface AccountService {
    public AllAccountsEvent requestAllAccounts(RequestAllAccountsEvent requestAllAccountsEvent);
}
