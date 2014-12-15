package web.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.DBObject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import static org.springframework.util.Assert.notNull;

/**
 * Domain model to represent Users in our application. 
 * A APIUser is specifically is linked to an APIUser along with a Customer,
 thereby allowing us to link login credentials and information with an account internal to the system.
 The link with APIUser allows us to administrate security privileges to Users
 */
@Document(collection = "users")
public class APIUser implements UserDetails, Principal {
    
    /** ID of the current APIUser */
    @NotNull
    @JsonProperty("_id")
    private ObjectId id;
    
    /** Email of the current APIUser */
    @Email
    @NotNull
    private String emailAddress;
    
    /**
     * Hashed Password of the current APIUser 
     */
    @JsonIgnore
    private String hashedPassword;
        
    /*
     * A List or Roles assigned to the APIUser, used in Security
     */
    private List<Role> roles = new ArrayList();

    /**
     * Default Constructor.
     */
    public APIUser() {
        super();
    }
    
    /**
     * Parameterised Constructor
     * @param id the ID for the User
     */
    public APIUser(ObjectId id) {
        this.id = id;
    }
    
    /**
     * Parameterised Constructor
     * @param apiUser the APIUser a User will be linked to
     * @param hashedPassword the hashed password of the User
     * @param role a role to be assigned to the User
     */
    public APIUser(final APIUser user, final String hashedPassword, Role role) {
        this();
        this.emailAddress = user.getEmailAddress().toLowerCase();
        this.hashedPassword = hashedPassword;
        this.roles.add(role);
    }
    
    /**
     * Parameterised Constructor
     * @param dbObject the Database Object that the User Details are to be pulled from.
     */
    public APIUser(DBObject dbObject) {
        this((ObjectId)dbObject.get("_id"));
        this.emailAddress = (String) dbObject.get("emailAddress");
        this.hashedPassword = (String) dbObject.get("hashedPassword");
        List<String> roles = (List<String>) dbObject.get("roles");
        deSerializeRoles(roles);
    }
    
    /**
     * Method to deserialise roles assigned to the APIUser
     * @param roles a List of roles assigned to the APIUser
     */
    private void deSerializeRoles(List<String> roles) {
        for (String role : roles) {
            this.addRole(Role.valueOf(role));
        }
    }
    
    /**
     * Method to determine authorities for a APIUser
     * @return Collection<? extends GrantedAuthority> Returns a collection of granted authorities for the APIUser
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : this.getRoles()) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
            authorities.add(authority);
        }
        return authorities;
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
    
    /**
     * Method to retrieve Password
     * @return String returns the APIUser's hashed password
     */
    @Override
    public String getPassword() {
        return hashedPassword;
    }
    
    /**
     * Standard getter returns the APIUser id as String
     * @return String id
     */
    public ObjectId getId() {
        return this.id;
    }
    
    /**
     * Standard setter for the id, with validation to ensure it is not NULL
     * @param id 
     */
    public void setId(ObjectId id) {
        notNull(id, "Mandatory argument 'id missing.'");
        this.id = id;
    }
    
    /**
     * Method to retrieve Username
     * @return String returns the APIUser's Username
     */
    @Override
    public String getUsername() {
        return this.getId().toString();
    }
    
    /**
     * Method to determine if a APIUser Account has Expired
     * @return boolean returns true if Account is non-expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /**
     * Method to determine if a APIUser Account is locked
     * @return boolean returns true if Account is not locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /**
     * Method to determine if a APIUser's Credentials has Expired
     * @return boolean returns true if Credentials is non-expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /**
     * Method to determine if a APIUser is enabled
     * @return boolean returns true if APIUser is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    /**
     * Method to retrieve a APIUser's Email Address
     * @return String the APIUser Email Address
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    /**
     * Method to set APIUser Email Address
     * @param emailAddress the Email Address to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Method to retrieve APIUser's hashed password 
     * @return String the APIUser's hashed password
     */
    public String getHashedPassword() {
        return hashedPassword;
    }
    
    /**
     * Method to set a APIUser's hashed password
     * @param hashedPassword the hashed password to set
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    
    /**
     * Method to retrieve a APIUser's roles
     * @return List<Role> a list of roles assigned to the APIUser
     */
    public List<Role> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }
    
    /**
     * Method to add a APIUser Role
     * @param role the role to add to the APIUser
     */
    public void addRole(Role role) {
        this.roles.add(role);
    }
    
    /**
     * Method to check if a APIUser has a certain role
     * @param role the role to check exists
     * @return boolean true if the user has the role specified
     */
    public boolean hasRole(Role role) {
        return (this.roles.contains(role));
    }
}
