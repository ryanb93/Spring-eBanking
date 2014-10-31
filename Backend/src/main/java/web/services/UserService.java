package web.services;

import web.domain.ApiUser;
import web.events.users.CreateUserRequest;

public interface UserService {

    public ApiUser createUser(final CreateUserRequest createUserRequest);

    public ApiUser authenticate(String username, String password);

    public ApiUser getUser(String userId);

    public ApiUser saveUser(String userId, CreateUserRequest request);

}
