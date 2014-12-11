package web.services.interfaces;

import web.domain.ApiUser;

public interface UserServiceInterface {

    public ApiUser createUser(ApiUser user, String password);

}
