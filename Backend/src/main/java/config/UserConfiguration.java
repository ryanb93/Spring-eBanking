package config;

import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.repository.UserRepository;
import web.services.UserService;
import web.services.eventHandlers.UserServiceEventHandler;

/**
 *  This class sets up and configures users in our application.
 *  Includes:
 *      - Validation
 *      - Services
 *      - DB access
 */
@Configuration
public class UserConfiguration {
    
    /** */
    @Autowired
    private UserRepository userRepository;
    
    /** */
    @Autowired
    private Validator validator;
    
    /** */
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Validates instances of Beans in our application
     * @return Validator
     */
    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    /**
     * Sets up and creates the UserServiceEvent
     * @return UserService
     */
    @Bean
    public UserService userService() {
        return new UserServiceEventHandler(userRepository, validator, passwordEncoder);
    }
    
    /**
     * Sets up and creates the UserServiceEventHandler
     * @return UserServiceEventHandler
     */
    @Bean
    public UserServiceEventHandler userResource() {
        return new UserServiceEventHandler(userRepository, validator, passwordEncoder);
    }
}
