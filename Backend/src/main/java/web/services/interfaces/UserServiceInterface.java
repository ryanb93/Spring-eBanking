package web.services.interfaces;

import java.util.List;
import web.domain.ApiUser;
import web.domain.User;

public interface UserServiceInterface {

    public ApiUser createUser(ApiUser user, String password);
    public List<User> fetchAllMongoDbUsers();

}
