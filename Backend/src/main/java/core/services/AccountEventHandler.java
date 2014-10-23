package core.services;

import core.events.accounts.AllAccountsEvent;
import core.events.accounts.RequestAllAccountsEvent;
import core.repository.AccountsRepository;

public class AccountEventHandler implements AccountService {

    private final AccountsRepository accountsRepository;

    public AccountEventHandler(final AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public AllAccountsEvent requestAllAccounts(RequestAllAccountsEvent requestAllAccountsEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
