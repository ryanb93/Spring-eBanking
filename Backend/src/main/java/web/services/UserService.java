package web.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.domain.APIUser;
import web.repository.interfaces.UserRepositoryInterface;
import web.services.interfaces.UserServiceInterface;

/**
 * This class deals with all things users in our web application 
 * The classes methods use the repositories to edit database values and these
 * transactions include:
 *  - Creating a APIUser
 *  - Getting a user by their email (username)
 *  - Setting the APIUser into Spring security Authentication 
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
     * @return APIUser that has the associated username
     * @throws UsernameNotFoundException when the username is not found in DB
     */
    public APIUser getUserByUsername(String username) throws UsernameNotFoundException {
        APIUser user = userRepository.findByEmailAddress(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user for username: " + username);
        }
        return user;
    }
    
    /**
     * Method does a lookup in the DB for a given username.
     * Stores user information into Authentication. Good way to encapsulate 
     * common attributes that aren't security related in one location.
     * @param username in the application is the email address signed up with
     * @return UserDetails The details of the user.
     * @throws UsernameNotFoundException When a user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username.toLowerCase());
    }

    /**
     * Method will create a user on the Application
     * @param emailAddress emailAddress is linked with a APIUser for application 
     * @param password the users password 
     * @return ApiUser of the newly created user. 
     */
    @Override
    public APIUser createUser(String emailAddress, String password) {
                
        //Make sure the user does not already exist.
        if (userRepository.findByEmailAddress(emailAddress.toLowerCase()) != null) {
            throw new IllegalArgumentException("This user already exists.");
        }

        //Encode password from plaintext to SHA
        String hashedPassword = passwordEncoder.encode(password);
        //Create a new user and save it
        APIUser newUser = new APIUser(emailAddress, hashedPassword);
        newUser = userRepository.save(newUser);
        return newUser;

    }
    
    /**
     * Method will return all users in the DB
     * @return List<User> 
     */
    @Override
    public List<APIUser> requestAllUsers(){
       return userRepository.findAll();
    }
}
