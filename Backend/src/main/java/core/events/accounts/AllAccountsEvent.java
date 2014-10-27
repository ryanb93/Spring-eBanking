package core.events.accounts;

import core.events.ReadEvent;
import core.domain.Account;
import java.util.ArrayList;
import java.util.List;

public class AllAccountsEvent extends ReadEvent {

    private final List<String> accountIds;

    public AllAccountsEvent(List<Account> accounts) {
        this.accountIds = new ArrayList();
        accounts.stream().forEach((a) -> {
            this.accountIds.add(a.getAccountId());
        });
    }

    public List<String> getAccountIds() {
        return this.accountIds;
    }
    
}
