package core.events.transactions;

import core.domain.Transaction;

public class CreateTransactionEvent {

    private final Transaction transaction;

    public CreateTransactionEvent(final Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

}
