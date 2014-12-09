package core.services.eventHandlers;

import core.domain.Account;
import core.domain.Transaction;
import core.events.accounts.AccountDetailsEvent;
import core.events.accounts.RequestAccountDetailsEvent;
import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.CreateTransactionEvent;
import core.events.transactions.RequestAllTransactionsEvent;
import core.events.transactions.RequestTransactionDetailsEvent;
import core.events.transactions.TransactionDetailsEvent;
import core.repository.AccountRepository;
import core.repository.TransactionRepository;
import core.services.AccountService;
import core.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionEventHandler implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private AccountService accountService;

    @Override
    public TransactionDetailsEvent requestTransactionDetails(RequestTransactionDetailsEvent requestTransactionDetailsEvent) {
        return new TransactionDetailsEvent(transactionRepository.findOne(requestTransactionDetailsEvent.getTransactionId()));               
    }
    
    @Override
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent) {
        String accountId = requestAllTransactionsEvent.getAccountId();
        int page = requestAllTransactionsEvent.getPage();
        return new AllTransactionsEvent(transactionRepository.findAllByAccountId(accountId, page));               
    }

    @Override
    public CreateTransactionEvent requestNewTransaction(CreateTransactionEvent createTransactionEvent) {
        Transaction newTransaction = createTransactionEvent.getTransaction();
        RequestAccountDetailsEvent event = new RequestAccountDetailsEvent(newTransaction.getAccountId());
        AccountDetailsEvent accountEvent = accountService.requestAccountDetails(event);
        Account account = accountEvent.getAccount();
        account.setBalance(account.getBalance() + newTransaction.getValue());
        accountRepository.save(account);
        transactionRepository.save(newTransaction);
        return new CreateTransactionEvent(transactionRepository.findOne(newTransaction.getTransactionId()));
    }

}
