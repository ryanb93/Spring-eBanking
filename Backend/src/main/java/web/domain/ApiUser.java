package web.domain;

import java.security.Principal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import static org.springframework.util.Assert.notNull;

/**
 * Domain model to represent API Users in our application. 
 * An API User is specifically used for when making OAuth requests as it allows
 * us to manage each session, ensuring that a user is not able to abuse the API.
 * This security management is not done here.
 */
public class ApiUser implements Principal {
    
    /** Email of the current User */
    @Email
    @NotNull
    private String emailAddress;
    
    /** ID of the current User */
    @NotNull
    private String id;

    /**
     * Default constructor.
     * This is required by Jackson to generate
     * the JSON. Without it fails. 
     */
    public ApiUser() {
    }
    
    /**
     * Constructor that takes a User to get their email and ID
     * @param user 
     */
    public ApiUser(User user) {
        this.emailAddress = user.getEmailAddress();
        this.id = user.getId();
    }
    
    /**
     * Standard getter that returns the email address
     * @return String email
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    /**
     * Standard setter, with Validation to ensure its a valid email before being
     * set. 
     * @param emailAddress 
     */
    public void setEmailAddress(final String emailAddress) {
        notNull(emailAddress, "Mandatory argument 'emailAddress missing.'");
        this.emailAddress = emailAddress;
    }
    
    /**
     * Standard getter returns the APIUser id as String
     * @return String id
     */
    public String getId() {
        return id;
    }
    
    /**
     * Standard setter for the id, with validation to ensure it is not NULL
     * @param id 
     */
    public void setId(final String id) {
        notNull(id, "Mandatory argument 'id missing.'");
        this.id = id;
    }
    
    /**
     * Common name used by Principal security.
     * Calls the email getter and this is used. 
     * @return 
     */
    @Override
    public String getName() {
        return this.getEmailAddress();
    }
}
