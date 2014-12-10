package core.repository.custom;

import core.domain.Transaction;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomTransactionRepository {

    public List<Transaction> findAllByAccountNumber(String accountNumber, int page);
}
