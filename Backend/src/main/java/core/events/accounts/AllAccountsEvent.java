package core.events.accounts;

import core.events.ReadEvent;
import core.domain.Account;
import java.util.Collections;
import java.util.List;

public class AllAccountsEvent extends ReadEvent {

    private final List<Account> accounts;

    public AllAccountsEvent(List<Account> accounts) {
      this.accounts = Collections.unmodifiableList(accounts);
    }

    public List<Account> getAccounts() {
      return this.accounts;
    }
    
}
