package core.domain;

import org.junit.Test;

public class AccountTest {
    
  @Test
  public void nullNameTest() {
    Account a1 = new Account("12345678", "123456", AccountType.CURRENT);
  }
    
}