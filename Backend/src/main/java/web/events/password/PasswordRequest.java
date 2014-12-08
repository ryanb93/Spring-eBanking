package web.events.password;

import javax.validation.constraints.NotNull;

public class PasswordRequest {

    @NotNull
    private String password;

    public PasswordRequest() {}

    public PasswordRequest(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
