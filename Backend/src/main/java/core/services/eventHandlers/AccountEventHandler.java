package core.services.eventHandlers;

import core.domain.Account;
import core.events.accounts.AccountDetailsEvent;
import core.events.accounts.AllAccountsEvent;
import core.events.accounts.CreateAccountEvent;
import core.events.accounts.RequestAccountDetailsEvent;
import core.events.accounts.RequestAccountDetailsFromNumberEvent;
import core.events.accounts.RequestAllAccountsEvent;
import core.events.accounts.RequestNewAccountEvent;
import core.events.accounts.UpdateAccountBalanceEvent;
import core.repository.AccountRepository;
import core.services.AccountService;
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
        return new AllAccountsEvent(accountRepository.findAllByCustomerId(requestAllAccountsEvent.getCustomerId()));
    }

    @Override
    public AccountDetailsEvent requestAccountDetails(RequestAccountDetailsEvent requestAccountDetailsEvent) {
        return new AccountDetailsEvent(accountRepository.findOne(requestAccountDetailsEvent.getAccountId()));
    }

    @Override
    public AccountDetailsEvent requestAccountDetailsFromNumber(RequestAccountDetailsFromNumberEvent requestAccountDetailsFromNumberEvent) {
        return new AccountDetailsEvent(accountRepository.findByAccountNumber(requestAccountDetailsFromNumberEvent.getAccountNumber()));
    }

    @Override
    public UpdateAccountBalanceEvent updateAccountBalance(UpdateAccountBalanceEvent updateAccountBalanceEvent) {
        Account account = updateAccountBalanceEvent.getAccount();
        Double transactionValue = updateAccountBalanceEvent.getTransactionValue();
        if (account != null) {
            account.setBalance(account.getBalance() + transactionValue);
            accountRepository.save(account);
        }
        return new UpdateAccountBalanceEvent(account, transactionValue);
    }

}
