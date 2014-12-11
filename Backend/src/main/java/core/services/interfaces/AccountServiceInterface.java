package core.services.interfaces;

import core.domain.Account;
import java.util.List;

/**
 * Class is used to core things
 */
public interface AccountServiceInterface {

    public Account requestNewAccount(Account account);

    public List<Account> requestAllAccounts(String customerId);

    public Account requestAccountDetails(String accountId);

    public Account requestAccountDetailsFromNumber(String accountNumber);
    
    public void removeAccount(String accountId);

    public Account updateAccountBalance(String accountNumber, double transactionValue);
}
