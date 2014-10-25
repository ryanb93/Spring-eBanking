package core.events.accounts;

import core.domain.Account;
import core.events.CreateEvent;

public class CreateAccountEvent extends CreateEvent {
    
  private final Account account;

  public CreateAccountEvent(final Account account) {
    this.account = account;
  }
  
  public Account getAccount() {
      return this.account;
  }
}
