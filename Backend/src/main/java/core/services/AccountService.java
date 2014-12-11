package core.services;

import core.domain.Account;
import core.repository.interfaces.AccountRepositoryInterface;
import core.services.interfaces.AccountServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is the AccountService, which holds methods that may access
 * the MongoDB Database to manipulate Accounts
 */
public class AccountService implements AccountServiceInterface {
    
    /**
    * The Account Repository, required to save and retrieve Account
    */
    @Autowired
    private AccountRepositoryInterface accountRepository;
    
    /**
     * Method which creates a new Account in MongoDB
     * 
     * @param account the account we want to save into MongoDB
     * @return Account the newly saved Account
     */
    @Override
    public Account requestNewAccount(Account account) {
        accountRepository.save(account);
        return accountRepository.findOne(account.getAccountId());
    }
    
    /**
     * Method which returns all the Accounts for a given Customer
     * 
     * @param customerId the ID of the Customer whose accounts we want to retrieve
     * @return List<Account> a list of all the Accounts under the particular customer
     */
    @Override
    public List<Account> requestAllAccounts(String customerId) {
        return accountRepository.findAllByCustomerId(customerId);
    }

    /**
     * Method which returns the Account associated with a given Account ID
     * 
     * @param accountId the ID of the Account we want to retrieve
     * @return Account the Account with the ID we specify
     */
    @Override
    public Account requestAccountDetails(String accountId) {
        return accountRepository.findOne(accountId);
    }

    /**
     * Method which returns the Account associated with a given Account Number
     * 
     * @param accountNumber the Account Number of the Account we want to retrieve
     * @return Account the Account with the Account Number we specify
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
    @Override public void requestRemoveAccount(String accountId){
        accountRepository.delete(accountRepository.findOne(accountId));
    }

    /**
     * Method which updates the Account Balance of a given Account with a given Transaction Value
     * 
     * @param accountNumber the Account Number of the Account with the Balance we want to update
     * @param transactionValue the value we want to update the Balance with
     * @return Account the Account with the Balance successfully updated
     */
    @Override
    public Account requestUpdateAccountBalance(String accountNumber, double transactionValue) {
        
        Account account = requestAccountDetailsFromNumber(accountNumber);
        
        if (account != null) {
            account.setBalance(account.getBalance() + transactionValue);
            accountRepository.save(account);
        }
        return account;
    }
    
    /**
     * Method to return all Accounts stored in MongoDB
     * 
     * @return List<Account> a list of all the Accounts stored in MongoDB
     */
    @Override
    public List<Account> requestAllAccounts(){
        return accountRepository.findAll();
    }

}
