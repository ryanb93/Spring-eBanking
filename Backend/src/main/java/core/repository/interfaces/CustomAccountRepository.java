package core.repository.interfaces;

import core.domain.Account;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * The Account Repository interface for Custom methods
 */
@Repository
public interface CustomAccountRepository {
    
    /**
     * Method to retrieve all Accounts owned by a single Customer
     * 
     * @param customerId the ID of the Customer whose Accounts we want to retrieve
     * @return List<Account> a List of the Accounts owned by the Customer Specified 
     */
    public List<Account> findAllByCustomerId(String customerId);

    /**
     * Method to find an Account by its Account Number
     * 
     * @param accountNumber the Account Number of the Account we want to retrieve
     * @return Account the Account with the Account Number Specified
     */
    public Account findByAccountNumber(String accountNumber);
}
