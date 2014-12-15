package core.services.interfaces;

import core.domain.Transaction;
import core.exceptions.APIException;
import java.util.List;

/**
 * Interface for the Transaction Service.
 * This service acts as a layer between the view controllers and the database
 * repository.
 */
public interface TransactionServiceInterface {
    
    /**
     * Method which returns the details of a single transaction based on its
     * database ID.
     *
     * @param transactionId - ID of the Transaction we want details of.
     * @return The transaction object containing details.
     * @throws core.exceptions.APIException
     */
    public Transaction requestTransactionDetails(String transactionId) throws APIException;
    
    /**
     * Method which creates a new transaction, adds it to the repository and
     * updates the account which it is linked to with a new value.
     *
     * @param transaction - The transaction we want to save.
     * @return Transaction the newly saved Transaction
     * @throws core.exceptions.APIException
     */
    public Transaction requestNewTransaction(Transaction transaction) throws APIException;
    
    /**
     * Method to request Transactions owned by a single account.
     * 
     * This method will return 10 accounts maximum. The offset which this
     * starts from can be changed by modifying the page value. Setting page to 
     * 0 will give the 10 latest transactions, 1 will start the offset at 10, 2
     * will start the offset 20, etc.
     * 
     * @param accountNumber - The account to get transactions from.
     * @param page - The offset to start from.
     * @return List<Transaction> - The transactions for that page.
     */
    public List<Transaction> requestAllTransactions(String accountNumber, int page);
    
    /**
     * Method which removes a Transaction from MongoDB.
     *
     * @param transactionId - The ID of the transaction we want to remove..
     * @throws core.exceptions.APIException
     */
    public void requestRemoveTransaction(String transactionId) throws APIException;
    
    /**
     * Method which returns all Transactions from MongoDB.
     *
     * @return List<Transaction> A List of all transactions stored in MongoDb
     */
    public List<Transaction> requestAllTransactions();
    
}
