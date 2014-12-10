package core.services;

import core.events.accounts.AccountDetailsEvent;
import core.events.accounts.AllAccountsEvent;
import core.events.accounts.CreateAccountEvent;
import core.events.accounts.RequestAccountDetailsEvent;
import core.events.accounts.RequestAccountDetailsFromNumberEvent;
import core.events.accounts.RequestAllAccountsEvent;
import core.events.accounts.RequestNewAccountEvent;
import core.events.accounts.UpdateAccountBalanceEvent;

/**
 * Class is used to core things 
 */
public interface AccountService {
    public CreateAccountEvent requestNewAccount(RequestNewAccountEvent requestNewAccountEvent);
    public AllAccountsEvent requestAllAccounts(RequestAllAccountsEvent requestAllAccountsEvent);
    public AccountDetailsEvent requestAccountDetails(RequestAccountDetailsEvent requestAccountDetailsEvent);
    public AccountDetailsEvent requestAccountDetailsFromNumber(RequestAccountDetailsFromNumberEvent requestAccountDetailsFromNumberEvent);
    public UpdateAccountBalanceEvent updateAccountBalance(UpdateAccountBalanceEvent updateAccountBalanceEvent);
}
