package config;

import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import web.repository.UserRepository;
import web.services.UserService;
import web.services.eventHandlers.UserServiceEventHandler;

@Configuration
public class UserConfiguration {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private DefaultTokenServices tokenServices;
//
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//    
    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    @Bean
    public UserService userService() {
        return new UserServiceEventHandler(userRepository, validator, passwordEncoder);
    } 
    
    @Bean
    public UserServiceEventHandler userResource() {
        return new UserServiceEventHandler(userRepository, validator, passwordEncoder);
    }

//    @Bean
//    public PasswordResource passwordResource() {
//        return new PasswordResource();
//    }
//
//    @Bean
//    public VerificationResource verificationResource() {
//        return new VerificationResource();
//    }
//
//    @Bean
//    public MeResource meResource() {
//        return new MeResource();
//    }

}
