package core.services.eventHandlers;

import core.domain.Account;
import core.domain.Transaction;
import core.events.accounts.AccountDetailsEvent;
import core.events.accounts.RequestAccountDetailsFromNumberEvent;
import core.events.accounts.UpdateAccountBalanceEvent;
import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.CreateTransactionEvent;
import core.events.transactions.RequestAllTransactionsEvent;
import core.events.transactions.RequestCreateTransactionEvent;
import core.events.transactions.RequestTransactionDetailsEvent;
import core.events.transactions.TransactionDetailsEvent;
import core.repository.TransactionRepository;
import core.services.AccountService;
import core.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionEventHandler implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    /**
     * Method which returns the details of a single transaction based on its database ID.
     * 
     * @param requestTransactionDetailsEvent - Event containing transaction ID.
     * @return The transaction object containing details.
     */
    @Override
    public TransactionDetailsEvent requestTransactionDetails(RequestTransactionDetailsEvent requestTransactionDetailsEvent) {
        //Extract the transaction ID from the request event.
        String transactionId = requestTransactionDetailsEvent.getTransactionId();
        //Get the transaction from the repository.
        Transaction transaction = transactionRepository.findOne(transactionId);
        //Return the transaction details event.
        return new TransactionDetailsEvent(transaction);               
    }
    
    /**
     * Method which returns the details of a all transactions for a given account.
     * 
     * @param requestAllTransactionsEvent - Event containing the request for all transactions of an account.
     * @return The transaction object containing all transaction details.
     */
    @Override
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent) {
        String accountNumber = requestAllTransactionsEvent.getAccountNumber();
        int page = requestAllTransactionsEvent.getPage();
        return new AllTransactionsEvent(transactionRepository.findAllByAccountNumber(accountNumber, page));
    }

    /**
     * Method which creates a new transaction, adds it to the repository and updates the 
     * account which it is linked to with a new value.
     * 
     * @param createTransactionEvent - The transaction request.
     * @return Event object containing the newly created transaction.
     */
    @Override
    public CreateTransactionEvent requestNewTransaction(RequestCreateTransactionEvent createTransactionEvent) {
        //Get the transaction object from the event.
        Transaction newTransaction = createTransactionEvent.getTransaction();
        //Get the number of the account to add the transaction to.
        String accountNumber = newTransaction.getAccountNumber();
        //Create a new account details request event for this account.
        RequestAccountDetailsFromNumberEvent event = new RequestAccountDetailsFromNumberEvent(accountNumber);
        //Send this request to the account service.
        AccountDetailsEvent accountEvent = accountService.requestAccountDetailsFromNumber(event); 
        //Get the account object from the request.
        Account account = accountEvent.getAccount();
        //Create a balance update event.
        UpdateAccountBalanceEvent balanceEvent = new UpdateAccountBalanceEvent(account, newTransaction.getValue());
        //Send the balance update event to the account service.
        accountService.updateAccountBalance(balanceEvent);
        //Save the new transaction to the repository.
        transactionRepository.save(newTransaction);
        //Get the newly created transaction back from the repository.
        Transaction created = transactionRepository.findOne(newTransaction.getTransactionId());
        return new CreateTransactionEvent(created);
    }

}
