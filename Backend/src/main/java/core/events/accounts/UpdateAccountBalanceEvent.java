package core.events.accounts;

import core.domain.Account;

public class UpdateAccountBalanceEvent {
    
    private final Account account;
    private final Double transactionValue;
    
    public UpdateAccountBalanceEvent(Account account, Double transactionValue){
        this.account = account;
        this.transactionValue = transactionValue;
    }
    
    public Account getAccount() {
        return this.account;
    }
    
    public Double getTransactionValue() {
        return this.transactionValue;
    }
    
}
