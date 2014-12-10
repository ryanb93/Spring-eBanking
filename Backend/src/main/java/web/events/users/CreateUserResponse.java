package web.events.users;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import web.domain.ApiUser;

/**
 * An Event that captures data about creating a new user.
 */
public class CreateUserResponse {

    /** The API user */
    private ApiUser apiUser;
    
    /** The access token */
    private OAuth2AccessToken oAuth2AccessToken;

    /**
     * Empty default constructor needed by Spring to create an CreateUserResponse
     * object from the JSON request body. 
     * 
     * It creates an empty object and then goes through all the setters and
     * sets the values based on the JSON key/value pairs.
     */
    public CreateUserResponse() {
    }

    /**
     * Constructor for a new CreateUserResponse object.
     * 
     * @param apiUser - The ApiUser object.
     * @param oAuth2AccessToken - The access token.
     */
    public CreateUserResponse(final ApiUser apiUser, OAuth2AccessToken oAuth2AccessToken) {
        this.apiUser = apiUser;
        this.oAuth2AccessToken = oAuth2AccessToken;
    }

    /**
     * Returns the API user.
     * @return the API user.
     */
    public ApiUser getApiUser() {
        return this.apiUser;
    }

    /**
     * Returns the access token.
     * @return the access token.
     */
    public OAuth2AccessToken getOAuth2AccessToken() {
        return this.oAuth2AccessToken;
    }
}
