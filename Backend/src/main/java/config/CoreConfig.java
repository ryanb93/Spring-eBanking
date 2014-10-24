package config;

import core.services.CustomerEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {
   
  @Bean
  public CustomerEventHandler createService() {
    return new CustomerEventHandler();
  }
  
}
