package web.events.users;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import web.domain.ApiUser;

public class CreateUserRequest {

    @NotNull
    @Valid
    private ApiUser user;

    @NotNull
    @Valid
    private String password;


    public CreateUserRequest() {
    }

    public CreateUserRequest(final ApiUser user, final String password) {
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
