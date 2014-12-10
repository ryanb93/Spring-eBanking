package config;

import core.services.eventHandlers.AccountEventHandler;
import core.services.eventHandlers.CustomerEventHandler;
import core.services.eventHandlers.TransactionEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class defines and creates the Event Handler services in our application.
 * Basically means it creates instances of these services to be used throughout
 * for transferring data from controllers to the services, before entering the 
 * database. A good way to encapsulate the data.
 */
@Configuration
public class ServiceConfig {
    
    /**
     * Sets up the CustomerEventHandler for capturing data from controllers.
     * @return CustomerEventHandler
     */
    @Bean
    public CustomerEventHandler createCustomerEventService() {
        return new CustomerEventHandler();
    }
    
    /**
     * Sets up the TransactionEventHandler for capturing data from controllers.
     * @return TransactionEventHandler
     */
    @Bean
    public TransactionEventHandler createTransactionEventService() {
        return new TransactionEventHandler();
    }
    
    /**
     * Sets up the AccountEventHandler for capturing data from controllers.
     * @return AccountEventHandler
     */
    @Bean
    public AccountEventHandler createAccountEventService() {
        return new AccountEventHandler();
    }

}
