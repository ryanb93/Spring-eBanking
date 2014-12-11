package web.domain;

import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Domain model to represent Users in our application. 
 * A User is specifically is linked to an APIUser along with a Customer,
 * thereby allowing us to link login credentials and information with an account internal to the system.
 * The link with APIUser allows us to administrate security privileges to Users
 */
@Document(collection = "app_user")
public class User extends BaseEntity implements UserDetails {
    
    /*
    *Email of the current User 
    */
    private String emailAddress;
    
    /**
     * Hashed Password of the current User 
     */
    private String hashedPassword;
    
    /*
    * Boleaan to detect whether a User is verified to access certain resources
    */
    private Boolean verified = false;
    
    /*
     * A List or Roles assigned to the User, used in Security
     */
    private List<Role> roles = new ArrayList();

    /**
     * Default Constructor.
     */
    public User() {
        super();
    }
    
    /**
     * Parameterised Constructor
     * @param id the ID for the User
     */
    public User(String id) {
        super(id);
    }
    
    /**
     * Parameterised Constructor
     * @param apiUser the APIUser a User will be linked to
     * @param hashedPassword the hashed password of the User
     * @param role a role to be assigned to the User
     */
    public User(final ApiUser apiUser, final String hashedPassword, Role role) {
        this();
        this.emailAddress = apiUser.getEmailAddress().toLowerCase();
        this.hashedPassword = hashedPassword;
        this.roles.add(role);
    }
    
    /**
     * Parameterised Constructor
     * @param dbObject the Database Object that the User Details are to be pulled from.
     */
    public User(DBObject dbObject) {
        this((String) dbObject.get("_id"));
        this.emailAddress = (String) dbObject.get("emailAddress");
        this.hashedPassword = (String) dbObject.get("hashedPassword");
        this.verified = (Boolean) dbObject.get("verified");
        List<String> roles = (List<String>) dbObject.get("roles");
        deSerializeRoles(roles);
    }
    
    /**
     * Method to deserialise roles assigned to the User
     * @param roles a List of roles assigned to the User
     */
    private void deSerializeRoles(List<String> roles) {
        for (String role : roles) {
            this.addRole(Role.valueOf(role));
        }
    }
    
    /**
     * Method to determine authorities for a User
     * @return Collection<? extends GrantedAuthority> Returns a collection of granted authorities for the User
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
     * Method to retrieve Password
     * @return String returns the User's hashed password
     */
    @Override
    public String getPassword() {
        return hashedPassword;
    }
    
    /**
     * Method to retrieve Username
     * @return String returns the User's Usernale
     */
    @Override
    public String getUsername() {
        return getId();
    }
    
    /**
     * Method to determine if a User Account has Expired
     * @return boolean returns true if Account is non-expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /**
     * Method to determine if a User Account is locked
     * @return boolean returns true if Account is not locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /**
     * Method to determine if a User's Credentials has Expired
     * @return boolean returns true if Credentials is non-expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /**
     * Method to determine if a User is enabled
     * @return boolean returns true if User is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    /**
     * Method to retrieve a User's Email Address
     * @return String the User Email Address
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    /**
     * Method to set User Email Address
     * @param emailAddress the Email Address to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    /**
     * Method to determine if a User is verified
     * @return boolean returns true if User is verified
     */
    public Boolean isVerified() {
        return verified;
    }
    
    /**
     * Method to set whether a User is verified
     * @param verified value of verified to set (true or false)
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    
    /**
     * Method to retrieve User's hashed password 
     * @return String the User's hashed password
     */
    public String getHashedPassword() {
        return hashedPassword;
    }
    
    /**
     * Method to set a User's hashed password
     * @param hashedPassword the hashed password to set
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    
    /**
     * Method to retrieve a User's roles
     * @return List<Role> a list of roles assigned to the User
     */
    public List<Role> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }
    
    /**
     * Method to add a User Role
     * @param role the role to add to the User
     */
    public void addRole(Role role) {
        this.roles.add(role);
    }
    
    /**
     * Method to check if a User has a certain role
     * @param role the role to check exists
     * @return boolean true if the user has the role specified
     */
    public boolean hasRole(Role role) {
        return (this.roles.contains(role));
    }
}
