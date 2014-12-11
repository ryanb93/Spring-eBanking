package core.services;

import core.domain.Account;
import core.domain.Transaction;
import core.domain.TransactionType;
import core.repository.TransactionRepository;
import core.services.interfaces.AccountServiceInterface;
import core.services.interfaces.TransactionServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionService implements TransactionServiceInterface {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountServiceInterface accountService;

    /**
     * Method which returns the details of a single transaction based on its database ID.
     * 
     * @param requestTransactionDetailsEvent - Event containing transaction ID.
     * @return The transaction object containing details.
     */
    @Override
    public Transaction requestTransactionDetails(String transactionId) {
        //Return the transaction details event.
        return transactionRepository.findOne(transactionId);               
    }
    
    /**
     * Method which returns the details of a all transactions for a given account.
     * 
     * @param requestAllTransactionsEvent - Event containing the request for all transactions of an account.
     * @return The transaction object containing all transaction details.
     */
    @Override
    public List<Transaction> requestAllTransactions(String accountNumber, int page) {
        return transactionRepository.findAllByAccountNumber(accountNumber, page);
    }
    
        /**
     * Method which removes a Transaction from MongoDB.
     * 
     * @param transactionId - The ID of the transaction we want to remove..
     */
     @Override
     public void removeTransaction(String transactionId) {
        transactionRepository.delete(transactionRepository.findOne(transactionId));
     }

    /**
     * Method which creates a new transaction, adds it to the repository and updates the 
     * account which it is linked to with a new value.
     * 
     * @param createTransactionEvent - The transaction request.
     * @return Event object containing the newly created transaction.
     */
    @Override
    public Transaction requestNewTransaction(Transaction transaction) {
        
        //String accountNumber = newTransaction.getAccountNumber();
        
        String senderAccountNumber = transaction.getSenderAccountNumber();
        String recipientAccountNumber = transaction.getRecipientAccountNumber();
        
        Account senderAccount = accountService.requestAccountDetailsFromNumber(senderAccountNumber);
        Account recipientAccount = accountService.requestAccountDetailsFromNumber(recipientAccountNumber);
        
        //RequestAccountDetailsFromNumberEvent event = new RequestAccountDetailsFromNumberEvent(accountNumber);
        
        //AccountDetailsEvent accountEvent = accountService.requestAccountDetailsFromNumber(event);
        
        //Account account = accountEvent.getAccount();
         if (senderAccount != null){
            accountService.updateAccountBalance(senderAccountNumber, -transaction.getValue());
            transaction.setAccountNumber(senderAccount.getAccountNumber());
            if (recipientAccount != null){
                if (transaction.getTransactionType() == null){
                transaction.setTransactionType(TransactionType.BACS);
                }
            }
            transactionRepository.save(transaction);
        }
         
        if (recipientAccount != null){
            accountService.updateAccountBalance(recipientAccountNumber, transaction.getValue());
            
            Transaction recipientTransaction = transaction;
             if (recipientTransaction.getTransactionType() == null){
             recipientTransaction.setTransactionType(TransactionType.BACS);
             }
            recipientTransaction.clearTransactionId();
            recipientTransaction.setAccountNumber(recipientAccount.getAccountNumber());
            if (senderAccount != null){
                recipientTransaction.setTransactionType(TransactionType.BACS);
            }
            transactionRepository.save(recipientTransaction);    
        }
        return transactionRepository.findOne(transaction.getTransactionId());

    }

}
