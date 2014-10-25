package core.events.accounts;

import core.domain.Account;
import core.events.CreatedEvent;

/**
 * This is a class that is returned to a service once an
 * account has been created in the repository.
 * 
 * It contains the account that has just been created.
 */
public class AccountCreatedEvent extends CreatedEvent {
  
  //The new account.
  private final Account account;

  /**
   * Constructor which takes the new account as a parameter.
   * 
   * @param account - The new account created.
   */
  public AccountCreatedEvent(final Account account) {
    this.account = account;
  }

  /**
   * Returns the new account created.
   * 
   * @return the new account object
   */
  public Account getAccount() {
    return this.account;
  }
  
}
