package web.services;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.domain.ApiUser;
import web.domain.Role;
import web.domain.User;
import web.repository.UserRepository;
import web.services.interfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(UserServiceInterface.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(final UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username.toLowerCase());
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user for username: " + username);
        }
        return user;
    }

    @Override
    public ApiUser createUser(ApiUser user, String password) {
        final String emailAddress = user.getEmailAddress().toLowerCase();
        if (userRepository.findByEmailAddress(emailAddress) == null) {
            String hashedPassword = passwordEncoder.encode(password);
            User newUser = new User(user, hashedPassword, Role.ROLE_USER);
            newUser = userRepository.save(newUser);
            return new ApiUser(newUser);
        } else {
            throw new IllegalArgumentException("This user already exists.");
        }
    }
    
    @Override
    public List<User> fetchAllMongoDbUsers(){
       return userRepository.findAll();
    }
}
