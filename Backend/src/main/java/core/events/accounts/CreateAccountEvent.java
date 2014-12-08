package core.events.accounts;

import core.domain.Account;

/**
 * This is the class that a service should use to make a new account.
 */
public class CreateAccountEvent {
    
  private final Account account;

  public CreateAccountEvent(final Account account) {
    this.account = account;
  }
  
  public Account getAccount() {
      return this.account;
  }
}
