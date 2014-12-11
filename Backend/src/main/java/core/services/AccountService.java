package core.services;

import core.domain.Account;
import core.repository.AccountRepository;
import core.services.interfaces.AccountServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 */
public class AccountService implements AccountServiceInterface {
    
    /** */
    @Autowired
    private AccountRepository accountRepository;
    
    /**
     * 
     * @param account
     * @return Account
     */
    @Override
    public Account requestNewAccount(Account account) {
        accountRepository.save(account);
        return accountRepository.findOne(account.getAccountId());
    }
    
    /**
     * 
     * @param customerId
     * @return List<Account>
     */
    @Override
    public List<Account> requestAllAccounts(String customerId) {
        return accountRepository.findAllByCustomerId(customerId);
    }

    /**
     * 
     * @param accountId
     * @return Account
     */
    @Override
    public Account requestAccountDetails(String accountId) {
        return accountRepository.findOne(accountId);
    }

    /**
     * 
     * @param accountNumber
     * @return Account
     */
    @Override
    public Account requestAccountDetailsFromNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    /**
     * Method which removes an Account from MongoDB.
     * 
     * @param accountId - The ID of the account we want to remove..
     */
    @Override public void removeAccount(String accountId){
        accountRepository.delete(accountRepository.findOne(accountId));
    }

    /**
     * 
     * @param accountNumber
     * @param transactionValue
     * @return Account
     */
    @Override
    public Account updateAccountBalance(String accountNumber, double transactionValue) {
        
        Account account = requestAccountDetailsFromNumber(accountNumber);
        
        if (account != null) {
            account.setBalance(account.getBalance() + transactionValue);
            accountRepository.save(account);
        }
        return account;
    }
    
    /**
     * 
     * @return List<Account>
     */
    @Override
    public List<Account> fetchAllMongoDbAccounts(){
        return accountRepository.findAll();
    }

}
