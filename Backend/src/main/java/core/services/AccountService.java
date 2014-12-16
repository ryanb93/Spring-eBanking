package core.services;

import core.domain.Account;
import core.exceptions.APIException;
import core.repository.interfaces.AccountRepository;
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
    private AccountRepository accountRepository;
    
    /**
     * Method which creates a new Account in MongoDB
     * 
     * @param account the account we want to save into MongoDB
     * @return Account the newly saved Account
     */
    @Override
    public Account requestNewAccount(Account account) throws APIException {
        Account saved = accountRepository.save(account);
        if(saved == null) throw new APIException("Could not create new account.");
        return saved;
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
    public Account requestAccountDetails(String accountId) throws APIException {
        Account account = accountRepository.findOne(accountId);
        if(account == null) throw new APIException("Could not find account.");
        return account;
    }

    /**
     * Method which returns the Account associated with a given Account Number
     * 
     * @param accountNumber the Account Number of the Account we want to retrieve
     * @return Account the Account with the Account Number we specify
     * @throws core.exceptions.APIException
     */
    @Override
    public Account requestAccountDetailsFromNumber(String accountNumber) throws APIException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if(account == null) throw new APIException("Could not find account.");
        return account;
    }
    
    /**
     * Method which removes an Account from MongoDB.
     * 
     * @param accountId - The ID of the account we want to remove..
     */
    @Override public void requestRemoveAccount(String accountId) throws APIException {
        Account account = accountRepository.findOne(accountId);
        if(account == null) throw new APIException("Could not find account.");
        accountRepository.delete(account);
        account = accountRepository.findOne(accountId);
        if(account != null) throw new APIException("Account could not be deleted.");    
    }

    /**
     * Method which updates the Account Balance of a given Account with a given Transaction Value
     * 
     * @param accountNumber the Account Number of the Account with the Balance we want to update
     * @param transactionValue the value we want to update the Balance with
     * @return Account the Account with the Balance successfully updated
     */
    @Override
    public Account requestUpdateAccountBalance(String accountNumber, double transactionValue) throws APIException {
        Account account = requestAccountDetailsFromNumber(accountNumber);
        if(account == null) throw new APIException("Could not find account.");
        account.setBalance(account.getBalance() + transactionValue);
        Account savedAccount = accountRepository.save(account);
        if(savedAccount == null) throw new APIException("Could not save account.");
        return savedAccount;
    }
    
    /**
     * Method to return all Accounts stored in MongoDB
     * 
     * @return List<Account> a list of all the Accounts stored in MongoDB
     */
    @Override
    public List<Account> requestAllAccounts() {
        return accountRepository.findAll();
    }

}
