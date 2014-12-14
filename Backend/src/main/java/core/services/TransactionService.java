package core.services;

import core.domain.Account;
import core.domain.Transaction;
import core.domain.TransactionType;
import core.exceptions.APIException;
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
     * @throws core.exceptions.APIException
     */
    @Override
    public Transaction requestTransactionDetails(String transactionId) throws APIException {
        //Return the transaction details event.
        Transaction transaction = transactionRepository.findOne(transactionId);
        if(transaction == null) throw new APIException("Transaction not found.");
        return transaction;
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
    public void requestRemoveTransaction(String transactionId) throws APIException {
        Transaction transaction = transactionRepository.findOne(transactionId);
        if(transaction == null) throw new APIException("Transaction does not exist.");
        transactionRepository.delete(transaction);
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
     * @throws core.exceptions.APIException
     */
    @Override
    public Transaction requestNewTransaction(Transaction transaction) throws APIException {

        if(transaction == null) {
            throw new APIException("No transaction given.");
        }
        
        //If the transaction value is greater than zero
        if(transaction.getValue() <= 0)  {
            throw new APIException("Transaction value can not be negative.");
        }
                
        //Get the account numbers.
        String accountNumber = transaction.getAccountNumber();
        String otherAccountNumber = transaction.getOtherAccountNumber();
        
        Account ownerAccount = null;
        Account otherAccount = null;
        
        //If a transaction type has not been sent assume it is a BACS.
        if (transaction.getTransactionType() == null) {
            transaction.setTransactionType(TransactionType.BACS);
        }
        
        //See if the account exists.
        try {
            ownerAccount = accountService.requestAccountDetailsFromNumber(accountNumber);
        }
        catch(APIException e) {
            throw new APIException("No account linked to this transaction.");
        }
          
        Transaction saved = null;
        
        //If money should be going out of the account.
        if(transaction.getSending()) {
            //Check the account has enough funds.
            if(transaction.getValue() > ownerAccount.getBalance()) {
                throw new APIException("Insufficient funds in account.");
            }
            //Attempt to save the transaction in the repository.
            saved = transactionRepository.save(transaction);
            //Update the account with the new value.
            accountService.requestUpdateAccountBalance(accountNumber, -transaction.getValue());
            
            try {
                //See if the other account exists on our system.
                otherAccount = accountService.requestAccountDetailsFromNumber(otherAccountNumber);
            }
            catch(APIException e) {
                //Sending money to an account outside of the bank.
            }
            
            if(otherAccount != null) {
                //Make a copy of the transaction.
                Transaction recipientTransaction = saved;
                //Clear the transaction ID.
                recipientTransaction.clearTransactionId();
                //Set the account number to this account.
                recipientTransaction.setAccountNumber(otherAccountNumber);
                //Set sending to false.
                recipientTransaction.setSending(false);
                //Set other account number to account who sent money.
                recipientTransaction.setOtherAccountNumber(accountNumber);
                //Set other sort code to account who sent money.
                recipientTransaction.setOtherSortCode(ownerAccount.getSortCode());
                //Save the transaction into the repository.
                Transaction otherSaved = transactionRepository.save(recipientTransaction);
                //Update the account with the new balance.
                accountService.requestUpdateAccountBalance(otherAccountNumber, otherSaved.getValue());
            }
            
        }
        //If money should be going into the account.
        else {
            saved = transactionRepository.save(transaction);
            accountService.requestUpdateAccountBalance(accountNumber, transaction.getValue());
        }
        
        return this.requestTransactionDetails(saved.getTransactionId());

    }

}
