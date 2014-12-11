package core.services.interfaces;

import core.domain.Account;
import java.util.List;

/**
 * Interface for the Account Service.
 * This service acts as a layer between the view controllers and the database
 * repository.
 */
public interface AccountServiceInterface {
    
    /**
     * Method which creates a new Account in MongoDB
     * 
     * @param account the account we want to save into MongoDB
     * @return Account the newly saved Account
     */
    public Account requestNewAccount(Account account);

    /**
     * Method which returns all the Accounts for a given Customer
     * 
     * @param customerId the ID of the Customer whose accounts we want to retrieve
     * @return List<Account> a list of all the Accounts under the particular customer
     */
    public List<Account> requestAllAccounts(String customerId);
    
    /**
     * Method to return all Accounts stored in MongoDB
     * 
     * @return List<Account> a list of all the Accounts stored in MongoDB
     */
    public List<Account> requestAllAccounts();
            
    /**
     * Method which returns the Account associated with a given Account ID
     * 
     * @param accountId the ID of the Account we want to retrieve
     * @return Account the Account with the ID we specify
     */
    public Account requestAccountDetails(String accountId);


    /**
     * Method which returns the Account associated with a given Account Number
     * 
     * @param accountNumber the Account Number of the Account we want to retrieve
     * @return Account the Account with the Account Number we specify
     */
    public Account requestAccountDetailsFromNumber(String accountNumber);
    
    /**
     * Method which removes an Account from MongoDB.
     * 
     * @param accountId - The ID of the account we want to remove..
     */
    public void requestRemoveAccount(String accountId);

    /**
     * Method which updates the Account Balance of a given Account with a given Transaction Value
     * 
     * @param accountNumber the Account Number of the Account with the Balance we want to update
     * @param transactionValue the value we want to update the Balance with
     * @return Account the Account with the Balance successfully updated
     */
    public Account requestUpdateAccountBalance(String accountNumber, double transactionValue);
    
}
