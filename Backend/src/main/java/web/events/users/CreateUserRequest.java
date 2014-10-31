package web.events.users;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import web.domain.ApiUser;
import web.events.password.PasswordRequest;


public class CreateUserRequest {

    @NotNull
    @Valid
    private ApiUser user;

    @NotNull
    @Valid
    private PasswordRequest password;


    public CreateUserRequest() {
    }

    public CreateUserRequest(final ApiUser user, final PasswordRequest password) {
        this.user = user;
        this.password = password;
    }

    public ApiUser getUser() {
        return user;
    }

    public void setUser(ApiUser user) {
        this.user = user;
    }

    @NotNull
    @Valid
    public PasswordRequest getPassword() {
        return password;
    }

    public void setPassword(PasswordRequest password) {
        this.password = password;
    }
}
