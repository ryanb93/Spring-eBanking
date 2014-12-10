package config;

import core.services.eventHandlers.AccountEventHandler;
import core.services.eventHandlers.CustomerEventHandler;
import core.services.eventHandlers.TransactionEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Bean
    public CustomerEventHandler createCustomerEventService() {
        return new CustomerEventHandler();
    }

    @Bean
    public TransactionEventHandler createTransactionEventService() {
        return new TransactionEventHandler();
    }

    @Bean
    public AccountEventHandler createAccountEventService() {
        return new AccountEventHandler();
    }

}
