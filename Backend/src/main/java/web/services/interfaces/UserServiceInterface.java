package web.services.interfaces;

import java.util.List;
import web.domain.ApiUser;
import web.domain.User;

/**
 * 
 */
public interface UserServiceInterface {

    /**
     * 
     * @param user
     * @param password
     * @return ApiUser
     */
    public ApiUser createUser(ApiUser user, String password);
    
    /**
     * 
     * @return List<User> 
     */
    public List<User> fetchAllMongoDbUsers();

}
