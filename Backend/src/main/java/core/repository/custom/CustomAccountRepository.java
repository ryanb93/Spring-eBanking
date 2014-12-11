package core.repository.custom;

import core.domain.Account;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * 
 */
@Repository
public interface CustomAccountRepository {
    
    /**
     * 
     * @param customerId
     * @return List<Account>
     */
    public List<Account> findAllByCustomerId(String customerId);

    /**
     * 
     * @param accountNumber
     * @return Account
     */
    public Account findByAccountNumber(String accountNumber);
}
