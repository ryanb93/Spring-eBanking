package web.services;

import java.util.List;
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

/**
 * 
 */
@Service
public class UserService implements UserServiceInterface, UserDetailsService {
    
    /** */
    private final UserRepository userRepository;
    
    /** */
    private final PasswordEncoder passwordEncoder;

    /**
     * 
     * @param userRepository
     * @param passwordEncoder 
     */
    @Autowired
    public UserService(final UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException 
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username.toLowerCase());
    }

    /**
     * 
     * @param username
     * @return User
     * @throws UsernameNotFoundException 
     */
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user for username: " + username);
        }
        return user;
    }

    /**
     * 
     * @param user
     * @param password
     * @return ApiUser
     */
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
    
    /**
     * 
     * @return List<User> 
     */
    @Override
    public List<User> fetchAllMongoDbUsers(){
       return userRepository.findAll();
    }
}
