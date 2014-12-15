package web.services.interfaces;

import java.util.List;
import web.domain.APIUser;

/**
 * Interface to ensure that the UserService implements the following methods. 
 * The methods here ensure that as a minimum in the service we can create and
 * get existing users from the DB
 */
public interface UserServiceInterface {

    /**
     * Method creates an API APIUser in the DB
     * @param user
     * @param password
     * @return ApiUser of the newly entered user
     */
    public APIUser createUser(APIUser user, String password);
    
    /**
     * Method returns a list of all the users in the DB
     * @return List<User> 
     */
    public List<APIUser> requestAllUsers();

}
