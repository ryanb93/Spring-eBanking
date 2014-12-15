package web.domain;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Token which holds data regarding the OAuth2 Authentication Refresh token.
 * 
 * Based on the work of: https://github.com/iainporter/oauth2-provider/
 */
public class OAuth2AuthenticationRefreshToken {
    
    /** The ID of the token. */
    private final String tokenId;
    
    /** Reference to the refresh token. */
    private final OAuth2RefreshToken oAuth2RefreshToken;
    
    /** Reference to the authentication object. */
    private final OAuth2Authentication authentication;

    /**
     * Constructor to create a new refresh token.
     * 
     * @param oAuth2RefreshToken - The OAuth2 Refresh token.
     * @param authentication - The Spring Security Authentication object.
     */
    public OAuth2AuthenticationRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication authentication) {
        this.oAuth2RefreshToken = oAuth2RefreshToken;
        this.authentication = authentication;
        this.tokenId = oAuth2RefreshToken.getValue();
    }
    
    /**
     * Returns the ID of the token.
     * 
     * @return String - Token ID
     */
    public String getTokenId() {
        return tokenId;
    }
    
    /**
     * Returns the OAuth2 Refresh token.
     * 
     * @return OAuth2RefreshToken - Refresh token
     */
    public OAuth2RefreshToken getoAuth2RefreshToken() {
        return oAuth2RefreshToken;
    }
    
    /**
     * Returns the OAuth Authentication object.
     * 
     * @return OAuth2Authentication - Authentication object.
     */
    public OAuth2Authentication getAuthentication() {
        return authentication;
    }
}
