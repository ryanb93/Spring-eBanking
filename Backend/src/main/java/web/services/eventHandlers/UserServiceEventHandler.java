package web.services.eventHandlers;

import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import static org.springframework.util.Assert.notNull;
import web.domain.ApiUser;
import web.domain.Role;
import web.domain.User;
import web.events.users.CreateUserRequest;
import web.repository.UserRepository;
import web.services.BaseService;
import web.services.UserService;


@Service
public class UserServiceEventHandler extends BaseService implements UserService, UserDetailsService {

    private Logger LOG = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceEventHandler(final UserRepository userRepository, Validator validator,
                           PasswordEncoder passwordEncoder) {
        super(validator);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return locateUser(username);
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
        User user = locateUser(username);
        if(!passwordEncoder.encode(password).equals(user.getHashedPassword())) {
            throw new IllegalArgumentException();
        }
        return new ApiUser(user);
    }

//    @Transactional
//    public ApiUser saveUser(String userId, UpdateUserRequest request) {
//        validate(request);
//        User user = userRepository.findById(userId);
//        if(user == null) {
////            throw new UserNotFoundException();
//            return null;
//        }
//        if(request.getFirstName() != null) {
//            user.setFirstName(request.getFirstName());
//        }
//        if(request.getLastName() != null) {
//            user.setLastName(request.getLastName());
//        }
//        if(request.getEmailAddress() != null) {
//            if(!request.getEmailAddress().equals(user.getEmailAddress())) {
//                user.setEmailAddress(request.getEmailAddress());
//                user.setVerified(false);
//            }
//        }
//        userRepository.save(user);
//        return new ApiUser(user);
//    }
//
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

    /**
     * Locate the user and throw an exception if not found.
     *
     * @param username
     * @return a User object is guaranteed.
     * @throws AuthenticationException if user not located.
     */
    private User locateUser(final String username) {
        notNull(username, "Mandatory argument 'username' missing.");
        User user = userRepository.findByEmailAddress(username.toLowerCase());
        if (user == null) {
            LOG.debug("Credentials [{}] failed to locate a user - hint, username.", username.toLowerCase());
//            throw new AuthenticationException();
            return null;
        }
        return user;
    }

    private User insertNewUser(final CreateUserRequest createUserRequest) {
        LOG.info("insertNewUser [-----------]");
        LOG.info("insertNewUser [{}]", createUserRequest.getUser().getEmailAddress());
        String hashedPassword = passwordEncoder.encode(createUserRequest.getPassword().getPassword());
        User newUser = new User(createUserRequest.getUser(), hashedPassword, Role.ROLE_USER);
        return userRepository.save(newUser);
    }

    @Override
    public ApiUser saveUser(String userId, CreateUserRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
