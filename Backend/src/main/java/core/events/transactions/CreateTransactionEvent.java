package core.events.transactions;

import core.domain.Transaction;
import core.events.CreateEvent;


public class CreateTransactionEvent extends CreateEvent {

  private final Transaction transaction;

  public CreateTransactionEvent(final Transaction transaction) {
    this.transaction = transaction;
  }
  
  public Transaction getTransaction() {
      return this.transaction;
  }
    
}
