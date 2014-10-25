package core.events.accounts;

import core.domain.Account;
import core.events.CreatedEvent;

public class AccountCreatedEvent extends CreatedEvent {
    
  private final String accountId;
  private final Account account;

  public AccountCreatedEvent(final String accountId, final Account account) {
    this.accountId = accountId;
    this.account = account;
  }

  public Account getAccount() {
    return this.account;
  }

  public String getAccountId() {
    return this.accountId;
  }
    
}
