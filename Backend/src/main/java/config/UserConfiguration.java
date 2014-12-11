package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.repository.interfaces.UserRepositoryInterface;
import web.services.interfaces.UserServiceInterface;
import web.services.UserService;

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
    private UserRepositoryInterface userRepository;
    
    /** */
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Sets up and creates the UserServiceEvent
     * @return UserService
     */
    @Bean
    public UserServiceInterface userService() {
        return new UserService(userRepository, passwordEncoder);
    }
    
    /**
     * Sets up and creates the UserServiceEventHandler
     * @return UserServiceEventHandler
     */
    @Bean
    public UserService userResource() {
        return new UserService(userRepository, passwordEncoder);
    }
}
