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
 * 
 */
@Document(collection = "app_user")
public class User extends BaseEntity implements UserDetails {
    
    /** */
    private String emailAddress;
    
    /** */
    private String hashedPassword;
    
    /** */
    private Boolean verified = false;
    
    /** */
    private List<Role> roles = new ArrayList();

    /**
     * 
     */
    public User() {
        super();
    }
    
    /**
     * 
     * @param id 
     */
    public User(String id) {
        super(id);
    }
    
    /**
     * 
     * @param apiUser
     * @param hashedPassword
     * @param role 
     */
    public User(final ApiUser apiUser, final String hashedPassword, Role role) {
        this();
        this.emailAddress = apiUser.getEmailAddress().toLowerCase();
        this.hashedPassword = hashedPassword;
        this.roles.add(role);
    }
    
    /**
     * 
     * @param dbObject 
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
     * 
     * @param roles 
     */
    private void deSerializeRoles(List<String> roles) {
        for (String role : roles) {
            this.addRole(Role.valueOf(role));
        }
    }
    
    /**
     * 
     * @return Collection<? extends GrantedAuthority>
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
     * 
     * @return String
     */
    @Override
    public String getPassword() {
        return hashedPassword;
    }
    
    /**
     * 
     * @return String
     */
    @Override
    public String getUsername() {
        return getId();
    }
    
    /**
     * 
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /**
     * 
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /**
     * 
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /**
     * 
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    /**
     * 
     * @return String
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    /**
     * 
     * @param emailAddress 
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    /**
     * 
     * @return Boolean
     */
    public Boolean isVerified() {
        return verified;
    }
    
    /**
     * 
     * @param verified 
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    
    /**
     * 
     * @return String
     */
    public String getHashedPassword() {
        return hashedPassword;
    }
    
    /**
     * 
     * @param hashedPassword 
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    
    /**
     * 
     * @return List<Role>
     */
    public List<Role> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }
    
    /**
     * 
     * @param role 
     */
    public void addRole(Role role) {
        this.roles.add(role);
    }
    
    /**
     * 
     * @param role
     * @return boolean
     */
    public boolean hasRole(Role role) {
        return (this.roles.contains(role));
    }
}
