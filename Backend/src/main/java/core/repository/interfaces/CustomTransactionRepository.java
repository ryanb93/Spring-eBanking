package core.repository.interfaces;

import core.domain.Transaction;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * The Transaction Repository interface for Custom Methods
 */
@Repository
public interface CustomTransactionRepository {

    /**
     * Method to find all Transactions owned by a specific Account
     * 
     * @param accountNumber the Account Number of the Account whose Transactions we want to retrieve
     * @param page the page number for displaying the Transactions
     * @return List<Transaction> a List of the Transactions owned by the specified AccountNumber
     */
    public List<Transaction> findAllByAccountNumber(String accountNumber, int page);
}
