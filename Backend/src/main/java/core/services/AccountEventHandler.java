package core.services;

import core.domain.Account;
import core.events.accounts.AllAccountsEvent;
import core.events.accounts.CreateAccountEvent;
import core.events.accounts.RequestAllAccountsEvent;
import core.events.accounts.RequestNewAccountEvent;
import core.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountEventHandler implements AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Override
    public CreateAccountEvent requestNewAccount(RequestNewAccountEvent requestNewAccountEvent) {
        Account newAccount = requestNewAccountEvent.getAccount();
        accountRepository.save(newAccount);
        return new CreateAccountEvent(accountRepository.findOne(newAccount.getAccountId()));
    }
    
    @Override
    public AllAccountsEvent requestAllAccounts(RequestAllAccountsEvent requestAllAccountsEvent) {
        return new AllAccountsEvent(accountRepository.findAll());
    }
    
}
