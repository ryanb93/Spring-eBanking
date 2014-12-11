package core.services.interfaces;

import core.domain.Transaction;
import java.util.List;

/**
 * 
 */
public interface TransactionServiceInterface {
    
    /**
     * 
     * @param transactionId
     * @return Transaction
     */
    public Transaction requestTransactionDetails(String transactionId);
    
    /**
     * 
     * @param transaction
     * @return Transaction
     */
    public Transaction requestNewTransaction(Transaction transaction);
    
    /**
     * 
     * @param accountNumber
     * @param page
     * @return List<Transaction>
     */
    public List<Transaction> requestAllTransactions(String accountNumber, int page);
    
    /**
     * 
     * @param transactionId 
     */
    public void removeTransaction(String transactionId);
    
    /**
     * 
     * @return List<Transaction>
     */
    public List<Transaction> fetchAllMongoDbTransactions();
}
