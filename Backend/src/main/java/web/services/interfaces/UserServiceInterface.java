package web.services.interfaces;

import web.domain.ApiUser;
import web.domain.User;

public interface UserServiceInterface {

    public ApiUser createUser(ApiUser user, String password);

    public ApiUser authenticate(String username, String password);

    public ApiUser getUser(String userId);

}
