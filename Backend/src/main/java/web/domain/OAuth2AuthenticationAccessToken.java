package web.domain;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * 
 */
public class OAuth2AuthenticationAccessToken {
    
    /** */
    private String tokenId;
    
    /** */
    private OAuth2AccessToken oAuth2AccessToken;
    
    /** */
    private String authenticationId;
    
    /** */
    private String userName;
    
    /** */
    private String clientId;
    
    /** */
    private OAuth2Authentication authentication;
    
    /** */
    private String refreshToken;
    
    /**
     * 
     */
    public OAuth2AuthenticationAccessToken() {
    }
    
    /**
     * 
     * @param oAuth2AccessToken
     * @param authentication
     * @param authenticationId 
     */
    public OAuth2AuthenticationAccessToken(final OAuth2AccessToken oAuth2AccessToken, final OAuth2Authentication authentication, final String authenticationId) {
        this.tokenId = oAuth2AccessToken.getValue();
        this.oAuth2AccessToken = oAuth2AccessToken;
        this.authenticationId = authenticationId;
        this.userName = authentication.getName();
        this.clientId = authentication.getOAuth2Request().getClientId();
        this.authentication = authentication;
        this.refreshToken = oAuth2AccessToken.getRefreshToken().getValue();
    }
    
    /**
     * 
     * @return String
     */
    public String getTokenId() {
        return tokenId;
    }
    
    /**
     * 
     * @return OAuth2AccessToken
     */
    public OAuth2AccessToken getoAuth2AccessToken() {
        return oAuth2AccessToken;
    }
    
    /**
     * 
     * @return String
     */
    public String getAuthenticationId() {
        return authenticationId;
    }
    
    /**
     * 
     * @return String
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * 
     * @return String
     */
    public String getClientId() {
        return clientId;
    }
    
    /**
     * 
     * @return OAuth2Authentication
     */
    public OAuth2Authentication getAuthentication() {
        return authentication;
    }
    
    /**
     * 
     * @return String
     */
    public String getRefreshToken() {
        return refreshToken;
    }
}
