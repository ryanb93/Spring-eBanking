package core.events.transactions;

public class RequestTransactionDetailsEvent {

    private final String transactionId;
    
    public RequestTransactionDetailsEvent(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getTransactionId() {
        return this.transactionId;
    }
    
}
