package core.services.eventHandlers;

import core.domain.Transaction;
import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.CreateTransactionEvent;
import core.events.transactions.RequestAllTransactionsEvent;
import core.events.transactions.RequestTransactionDetailsEvent;
import core.events.transactions.TransactionDetailsEvent;
import core.repository.TransactionRepository;
import core.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionEventHandler implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

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
        transactionRepository.save(newTransaction);
        return new CreateTransactionEvent(transactionRepository.findOne(newTransaction.getTransactionId()));
    }

}
