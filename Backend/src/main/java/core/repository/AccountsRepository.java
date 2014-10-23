package core.repository;

import core.domain.Account;
import java.util.List;
import java.util.UUID;

public interface AccountsRepository {

    Account save(Account order);

    void delete(UUID key);

    Account findById(UUID key);

    List<Account> findAll();
}