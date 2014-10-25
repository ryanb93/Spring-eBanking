package core.events.accounts;

import core.domain.Account;
import core.events.CreateEvent;

/**
 * This is the class that a service should use to make a new account.
 */
public class CreateAccountEvent extends CreateEvent {
    
  private final Account account;

  public CreateAccountEvent(final Account account) {
    this.account = account;
  }
  
  public Account getAccount() {
      return this.account;
  }
}
