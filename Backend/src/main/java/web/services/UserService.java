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
import web.repository.interfaces.UserRepositoryInterface;
import web.services.interfaces.UserServiceInterface;

/**
 * This class deals with all things users in our web application 
 * The classes methods use the repositories to edit database values and these
 * transactions include:
 *  - Creating a User
 *  - Getting a user by their email (username)
 *  - Setting the User into Spring security Authentication 
 *  - Getting all the Users
 */
@Service
public class UserService implements UserServiceInterface, UserDetailsService {
    
    /** Access to the Users in the DB */
    private final UserRepositoryInterface userRepository;
    
    /** Hasher for converting plaintext passwords to SHA */
    private final PasswordEncoder passwordEncoder;

    /**
     * Default constructor for the user service. 
     * It takes a repository and a password encoder 
     * @param userRepository
     * @param passwordEncoder 
     */
    @Autowired
    public UserService(final UserRepositoryInterface userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method does a lookup for a username in the repository.
     * The username is an email address
     * @param username in the application is the email address signed up with
     * @return User that has the associated username
     * @throws UsernameNotFoundException when the username is not found in DB
     */
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user for username: " + username);
        }
        return user;
    }
    
    /**
     * Method does a lookup in the DB for a given username.
     * Stores user information into Authentication. Good way to encapsulate 
     * common attributes that aren't security related in one location.
     * @param username
     * @return UserDetails 
     * @throws UsernameNotFoundException When a user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username.toLowerCase());
    }


    /**
     * Method will create a user on the Application
     * @param user APIUser is linked with a User for application 
     * @param password the users password 
     * @return ApiUser of the newly created user. 
     */
    @Override
    public ApiUser createUser(ApiUser user, String password) {
        
        //  Email address signed up with forced to lower case
        final String emailAddress = user.getEmailAddress().toLowerCase();
        
        // If the user email doesn't exist create the user
        if (userRepository.findByEmailAddress(emailAddress) == null) {
            
            //  Encode password from plaintext to SHA
            String hashedPassword = passwordEncoder.encode(password);
            
            //  Create a new user and save it
            User newUser = new User(user, hashedPassword, Role.ROLE_USER);
            newUser = userRepository.save(newUser);
            return new ApiUser(newUser);
        } else {
            // Else the email exists and an exception is thrown to indicate a user exists
            throw new IllegalArgumentException("This user already exists.");
        }
    }
    
    /**
     * Method will return all users in the DB
     * @return List<User> 
     */
    @Override
    public List<User> fetchAllMongoDbUsers(){
       return userRepository.findAll();
    }
}
