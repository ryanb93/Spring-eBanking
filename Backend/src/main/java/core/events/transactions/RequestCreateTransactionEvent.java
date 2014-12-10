package core.events.transactions;

import core.domain.Transaction;

public class RequestCreateTransactionEvent {

    private final Transaction transaction;

    public RequestCreateTransactionEvent(final Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

}
