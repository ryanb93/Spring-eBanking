package core.services;

import core.events.accounts.AllAccountsEvent;
import core.events.accounts.RequestAllAccountsEvent;

public class AccountsEventHandler implements AccountService {
    
    @Override
    public AllAccountsEvent requestAllAccounts(RequestAllAccountsEvent requestAllAccountsEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
