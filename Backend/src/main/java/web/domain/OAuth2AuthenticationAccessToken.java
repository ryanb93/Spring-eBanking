package web.domain;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Token which holds data regarding the OAuth2 Authentication Access token.
 * 
 * Based on the work of: https://github.com/iainporter/oauth2-provider/
 */
public class OAuth2AuthenticationAccessToken {
    
    /** The ID of the token. */
    private final String tokenId;
    
    /** A reference to the OAuth2 Access Token*/
    private final OAuth2AccessToken oAuth2AccessToken;
    
     /** The Authentication Object*/
    private final OAuth2Authentication authentication;  
    
    /** The ID of the authentication. */
    private final String authenticationId;
    
    /** The user name of the access token. */
    private final String userName;
    
    /** The ID of the client requesting the token. */
    private final String clientId;
    
    /** The refresh token. */
    private final String refreshToken;
    
    /**
     * Constructor for the OAuth authentication access token.
     * 
     * @param oAuth2AccessToken - The OAuth2 Access Token.
     * @param authentication - The Spring Security Authentication object. 
     * @param authenticationId - The ID of the authentication.
     */
    public OAuth2AuthenticationAccessToken(final OAuth2AccessToken oAuth2AccessToken, final OAuth2Authentication authentication, final String authenticationId) {
        
        assertNotNull(oAuth2AccessToken, "Missing OAuth2 Access Token.");
        assertNotNull(authentication, "Missing OAuth2 Authentication");
        assertNotNull(authenticationId, "Missing OAuth2 Authentication ID");
        
        this.tokenId = oAuth2AccessToken.getValue();
        this.oAuth2AccessToken = oAuth2AccessToken;
        this.authenticationId = authenticationId;
        this.userName = authentication.getName();
        this.clientId = authentication.getOAuth2Request().getClientId();
        this.authentication = authentication;
        this.refreshToken = oAuth2AccessToken.getRefreshToken().getValue();
    }
    
    /**
     * Gets the ID of the token.
     * 
     * @return String
     */
    public String getTokenId() {
        return this.tokenId;
    }
    
    /**
     * Gets the OAuth access token.
     * 
     * @return OAuth2AccessToken
     */
    public OAuth2AccessToken getoAuth2AccessToken() {
        return this.oAuth2AccessToken;
    }
    
    /**
     * Gets the authentication ID.
     * 
     * @return String
     */
    public String getAuthenticationId() {
        return this.authenticationId;
    }
    
    /**
     * Gets the user name.
     * 
     * @return String
     */
    public String getUserName() {
        return this.userName;
    }
    
    /**
     * Returns the client ID.
     * 
     * @return String
     */
    public String getClientId() {
        return this.clientId;
    }
    
    /**
     * Gets the OAuth authentication object.
     * 
     * @return OAuth2Authentication
     */
    public OAuth2Authentication getAuthentication() {
        return this.authentication;
    }
    
    /**
     * Gets the refresh token.
     * 
     * @return String - Refresh token.
     */
    public String getRefreshToken() {
        return this.refreshToken;
    }
}
