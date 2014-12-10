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
     * 
     * @param requestAllTransactionsEvent
     * @return 
     */
    @Override
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent) {
        String accountNumber = requestAllTransactionsEvent.getAccountNumber();
        int page = requestAllTransactionsEvent.getPage();
        return new AllTransactionsEvent(transactionRepository.findAllByAccountNumber(accountNumber, page));
    }

    /**
     * 
     * @param createTransactionEvent
     * @return 
     */
    @Override
    public CreateTransactionEvent requestNewTransaction(RequestCreateTransactionEvent createTransactionEvent) {
        Transaction newTransaction = createTransactionEvent.getTransaction();
        String accountNumber = newTransaction.getAccountNumber();
        RequestAccountDetailsFromNumberEvent event = new RequestAccountDetailsFromNumberEvent(accountNumber);
        AccountDetailsEvent accountEvent = accountService.requestAccountDetailsFromNumber(event);        
        Account account = accountEvent.getAccount();
        UpdateAccountBalanceEvent balanceEvent = new UpdateAccountBalanceEvent(account, newTransaction.getValue());
        accountService.updateAccountBalance(balanceEvent);
        transactionRepository.save(newTransaction);
        return new CreateTransactionEvent(transactionRepository.findOne(newTransaction.getTransactionId()));
    }

}
