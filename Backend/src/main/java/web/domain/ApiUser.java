package web.domain;

import java.security.Principal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import static org.springframework.util.Assert.notNull;

public class ApiUser implements Principal {

    @Email
    @NotNull
    private String emailAddress;
    private String id;

    public ApiUser() {
    }

    public ApiUser(User user) {
        this.emailAddress = user.getEmailAddress();
        this.id = user.getId();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        notNull(emailAddress, "Mandatory argument 'emailAddress missing.'");
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        notNull(id, "Mandatory argument 'id missing.'");
        this.id = id;
    }

    @Override
    public String getName() {
        return this.getEmailAddress();
    }
}
