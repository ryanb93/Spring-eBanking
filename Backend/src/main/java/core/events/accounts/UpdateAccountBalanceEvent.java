package core.events.accounts;

import core.domain.Account;

public class UpdateAccountBalanceEvent {

    private final String accountNumber;
    private final double transactionValue;
    
    public UpdateAccountBalanceEvent(String accountNumber, double transactionValue){
        this.accountNumber = accountNumber;
        this.transactionValue = transactionValue;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getTransactionValue() {
        return this.transactionValue;
    }

}
