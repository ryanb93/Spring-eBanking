package config;

import core.services.AccountService;
import core.services.CustomerService;
import core.services.TransactionService;
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
    public CustomerService createCustomerEventService() {
        return new CustomerService();
    }
    
    /**
     * Sets up the TransactionEventHandler for capturing data from controllers.
     * @return TransactionEventHandler
     */
    @Bean
    public TransactionService createTransactionEventService() {
        return new TransactionService();
    }
    
    /**
     * Sets up the AccountEventHandler for capturing data from controllers.
     * @return AccountEventHandler
     */
    @Bean
    public AccountService createAccountEventService() {
        return new AccountService();
    }

}
