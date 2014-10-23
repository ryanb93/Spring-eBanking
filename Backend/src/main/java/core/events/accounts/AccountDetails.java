package core.events.accounts;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AccountDetails {
    
    private final List<AccountDetails> accountDetails;
    
    public AccountDetails(List<AccountDetails> accounts) {
        this.accountDetails = Collections.unmodifiableList(accounts);
    }
    
    public Collection<AccountDetails> getAccountDetails() {
        return this.accountDetails;
    }
    
}
