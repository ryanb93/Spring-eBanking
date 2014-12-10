package core.events.transactions;

public class RequestAllTransactionsEvent {
  
    private final String accountNumber;
    private final int page;
    
    public RequestAllTransactionsEvent(String accountNumber, int page) {
        this.accountNumber = accountNumber;
        this.page = page;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public int getPage() {
        return this.page;
    }
    
}
