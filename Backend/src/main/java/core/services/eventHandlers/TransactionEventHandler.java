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
        String accountNumber = requestAllTransactionsEvent.getAccountNumber();
        int page = requestAllTransactionsEvent.getPage();
        return new AllTransactionsEvent(transactionRepository.findAllByAccountNumber(accountNumber, page));
    }

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
