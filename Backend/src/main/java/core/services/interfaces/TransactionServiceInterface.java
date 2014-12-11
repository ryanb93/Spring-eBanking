package core.services.interfaces;

import core.domain.Transaction;
import java.util.List;

public interface TransactionServiceInterface {
    public Transaction requestTransactionDetails(String transactionId);
    public Transaction requestNewTransaction(Transaction transaction);
    public List<Transaction> requestAllTransactions(String accountNumber, int page);
    public void removeTransaction(String transactionId);
}
