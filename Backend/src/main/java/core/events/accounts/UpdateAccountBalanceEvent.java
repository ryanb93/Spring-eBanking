package core.events.accounts;

import core.domain.Account;

public class UpdateAccountBalanceEvent {
    
    public final Account account;
    public final Double transactionValue;
    
    public UpdateAccountBalanceEvent(Account account, Double transactionValue){
        this.account = account;
        this.transactionValue = transactionValue;
    }
    
}
