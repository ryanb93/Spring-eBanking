package core.repository.custom;

import core.domain.Account;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomAccountRepository {
    public List<Account> findAllByCustomerId(String customerId);
}
