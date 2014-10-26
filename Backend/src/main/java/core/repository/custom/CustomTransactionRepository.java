package core.repository.custom;

import core.domain.Transaction;
import java.util.List;


public interface CustomTransactionRepository {
    public List<Transaction> findAllByAccountId(String accountId);
}
