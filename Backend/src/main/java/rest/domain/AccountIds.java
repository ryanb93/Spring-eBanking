package rest.domain;

import core.domain.Account;
import java.util.ArrayList;
import java.util.List;


public class AccountIds {
    
    private final List<String> accountIds;
    
    public AccountIds(List<Account> accounts) {
        this.accountIds = new ArrayList();
        for(Account account : accounts) {
            this.accountIds.add(account.getAccountId());
        }
    }
    
    public List<String> getAccountIds() {
        return this.accountIds;
    }

}
