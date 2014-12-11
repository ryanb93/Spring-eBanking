package core.services.interfaces;

import core.domain.Account;
import java.util.List;

/**
 * 
 */
public interface AccountServiceInterface {
    
    /**
     * 
     * @param account
     * @return Account
     */
    public Account requestNewAccount(Account account);

    /**
     * 
     * @param customerId
     * @return List<Account>
     */
    public List<Account> requestAllAccounts(String customerId);
    
    /**
     * 
     * @param accountId
     * @return Account
     */
    public Account requestAccountDetails(String accountId);

    /**
     * 
     * @param accountNumber
     * @return Account
     */
    public Account requestAccountDetailsFromNumber(String accountNumber);
    
    /**
     * 
     * @param accountId 
     */
    public void removeAccount(String accountId);

    /**
     * 
     * @param accountNumber
     * @param transactionValue
     * @return Account
     */
    public Account updateAccountBalance(String accountNumber, double transactionValue);
    
    /**
     * 
     * @return List<Account>
     */
    public List<Account> fetchAllMongoDbAccounts();
}
