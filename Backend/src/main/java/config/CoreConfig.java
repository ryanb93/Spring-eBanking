package config;

import core.services.AccountsEventHandler;
import core.services.CustomerEventHandler;
import core.services.ThirdPartyAppsEventHandler;
import core.services.TransactionEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
   
  @Bean
  public CustomerEventHandler createCustomerEventService() {
    return new CustomerEventHandler();
  }
  
  @Bean
  public ThirdPartyAppsEventHandler createThirdPartyAppEventService() {
      return new ThirdPartyAppsEventHandler();
  }
  
  @Bean
  public TransactionEventHandler createTransactionEventService() {
      return new TransactionEventHandler();
  }
  
  @Bean
  public AccountsEventHandler createAccountEventService() {
      return new AccountsEventHandler();
  }
  
}
