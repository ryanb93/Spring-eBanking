package core.events.transactions;

import core.events.RequestReadEvent;


public class RequestTransactionDetailsEvent extends RequestReadEvent {

    private final String transactionId;
    
    public RequestTransactionDetailsEvent(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getTransactionId() {
        return this.transactionId;
    }
    
}
