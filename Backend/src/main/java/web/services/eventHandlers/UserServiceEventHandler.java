package web.services.eventHandlers;

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
import org.springframework.util.Assert;
import web.domain.ApiUser;
import web.domain.Role;
import web.domain.User;
import web.events.users.CreateUserRequest;
import web.repository.UserRepository;
import web.services.UserService;


@Service
public class UserServiceEventHandler implements UserService, UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;


    @Autowired
    public UserServiceEventHandler(final UserRepository userRepository, Validator validator,
                           PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username.toLowerCase());
    }
    
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(username.toLowerCase());
        if(user == null) throw new UsernameNotFoundException("Could not find user for username: " + username);
        return user;
    }

    @Override
    public ApiUser createUser(final CreateUserRequest createUserRequest) {
        validate(createUserRequest);
        final String emailAddress = createUserRequest.getUser().getEmailAddress().toLowerCase();
        if (userRepository.findByEmailAddress(emailAddress) == null) {
            User newUser = insertNewUser(createUserRequest);
            return new ApiUser(newUser);
        } else {
            throw new IllegalArgumentException("This user already exists.");
        }
    }

    @Override
    public ApiUser authenticate(String username, String password) {
        Assert.notNull(username);
        Assert.notNull(password);
        User user = getUserByUsername(username);
        if(!passwordEncoder.encode(password).equals(user.getHashedPassword())) {
            throw new IllegalArgumentException();
        }
        return new ApiUser(user);
    }
    
    @Override
    public ApiUser getUser(String userId) {
        Assert.notNull(userId);
        User user = userRepository.findById(userId);
        if(user == null) {
//            throw new UserNotFoundException();
            return null;
        }
        return new ApiUser(user);
    }

    private User insertNewUser(final CreateUserRequest createUserRequest) {
        String hashedPassword = passwordEncoder.encode(createUserRequest.getPassword().getPassword());
        User newUser = new User(createUserRequest.getUser(), hashedPassword, Role.ROLE_USER);
        return userRepository.save(newUser);
    }
    
    protected void validate(Object request) {
        Set<? extends ConstraintViolation<?>> constraintViolations = validator.validate(request);
        if (constraintViolations.size() > 0) {
            throw new ValidationException();
        }
    }
}
