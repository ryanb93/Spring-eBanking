package core.services;

import core.domain.Account;
import core.domain.Transaction;
import core.domain.TransactionType;
import core.exceptions.InsufficientFundsException;
import core.repository.interfaces.TransactionRepository;
import core.services.interfaces.AccountServiceInterface;
import core.services.interfaces.TransactionServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is the TransactionService, which holds methods that may access
 * the MongoDB Database to manipulate Transactions
 */
public class TransactionService implements TransactionServiceInterface {

    /**
     * The Transaction Repository, required to save and retrieve Transactions
     */
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * The Account Service, required to call methods on Accounts      
     */
    @Autowired
    private AccountServiceInterface accountService;

    /**
     * Method which returns the details of a single transaction based on its
     * database ID.
     *
     * @param transactionId - ID of the Transaction we want details of.
     * @return The transaction object containing details.
     */
    @Override
    public Transaction requestTransactionDetails(String transactionId) {
        //Return the transaction details event.
        return transactionRepository.findOne(transactionId);
    }

    /**
     * Method to request Transactions owned by a single account.
     * This method will return 10 accounts maximum. The offset which this
     * starts from can be changed by modifying the page value. Setting page to 
     * 0 will give the 10 latest transactions, 1 will start the offset at 10, 2
     * will start the offset 20, etc.
     * 
     * @param accountNumber - The account to get transactions from.
     * @param page - The offset to start from.
     * @return List<Transaction> - The transactions for that page.
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
    public void requestRemoveTransaction(String transactionId) {
        transactionRepository.delete(transactionRepository.findOne(transactionId));
    }

    /**
     * Method which returns all Transactions from MongoDB.
     * 
     * @return List<Transaction> A List of all transactions stored in MongoDb
     */
    @Override
    public List<Transaction> requestAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Method which creates a new transaction, adds it to the repository and
     * updates the account which it is linked to with a new value.
     *
     * @param transaction - The transaction we want to save.
     * @return Transaction the newly saved Transaction
     * @throws core.exceptions.InsufficientFundsException
     */
    @Override
    public Transaction requestNewTransaction(Transaction transaction) throws InsufficientFundsException {

        //Get the account numbers for both the sender and recipient.
        String senderAccountNumber = transaction.getSenderAccountNumber();
        String recipientAccountNumber = transaction.getRecipientAccountNumber();
        
        // Pull the Sender and Recipient Account from MongoDb. If it does not exist, return null.
        Account senderAccount = accountService.requestAccountDetailsFromNumber(senderAccountNumber);
        Account recipientAccount = accountService.requestAccountDetailsFromNumber(recipientAccountNumber);
        
        //If the senderAccount is not null.
        if (senderAccount != null) {

            //Insufficient funds check.
            if(transaction.getValue() > senderAccount.getBalance()) {
                throw new InsufficientFundsException("Insufficient funds in this account.");
            }
            
            //Set the transaction account owner to this account.
            transaction.setAccountNumber(senderAccount.getAccountNumber());
            //If a transaction type has not been sent assume it is a BACS.
            if (transaction.getTransactionType() == null) {
                transaction.setTransactionType(TransactionType.BACS);
            }
            
            //Attempt to save the transaction in the repository.
            Transaction saved = transactionRepository.save(transaction);
            
            //If the transaction was successfully saved.
            if(saved != null) {
                accountService.requestUpdateAccountBalance(senderAccountNumber, -transaction.getValue());
            }
            else {
                return null;
            }
            
        }

        //If the recipient is in our system.
        if (recipientAccount != null) {
            //Make a copy of the transaction.
            Transaction recipientTransaction = transaction;
            //Clear the transaction ID.
            recipientTransaction.clearTransactionId();
            //If there is no transaction type set it to BACS.
            if (recipientTransaction.getTransactionType() == null) {
                recipientTransaction.setTransactionType(TransactionType.BACS);
            }
            //Set the account number to this account.
            recipientTransaction.setAccountNumber(recipientAccount.getAccountNumber());
            //Save the transaction into the repository.
            Transaction saved = transactionRepository.save(recipientTransaction);
            //If the transaction was saved successfully.
            if(saved != null) {
                //Update the account with the new balance.
                accountService.requestUpdateAccountBalance(recipientAccountNumber, transaction.getValue());
            }
        }
        return transactionRepository.findOne(transaction.getTransactionId());

    }

}
