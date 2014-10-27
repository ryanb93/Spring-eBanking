package core.services.eventHandlers;

import core.domain.Transaction;
import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.CreateTransactionEvent;
import core.events.transactions.RequestAllTransactionsEvent;
import core.repository.TransactionRepository;
import core.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionEventHandler implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent) {
        return new AllTransactionsEvent(transactionRepository.findAllByAccountId(requestAllTransactionsEvent.getAccountId()));               
    }

    @Override
    public CreateTransactionEvent requestNewTransaction(CreateTransactionEvent createTransactionEvent) {
        Transaction newTransaction = createTransactionEvent.getTransaction();
        transactionRepository.save(newTransaction);
        return new CreateTransactionEvent(transactionRepository.findOne(newTransaction.getTransactionId()));
    }

}
