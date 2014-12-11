package web.domain;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * 
 */
public class OAuth2AuthenticationRefreshToken {
    
    /** */
    private String tokenId;
    
    /** */
    private OAuth2RefreshToken oAuth2RefreshToken;
    
    /** */
    private OAuth2Authentication authentication;

    /**
     * 
     * @param oAuth2RefreshToken
     * @param authentication 
     */
    public OAuth2AuthenticationRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication authentication) {
        this.oAuth2RefreshToken = oAuth2RefreshToken;
        this.authentication = authentication;
        this.tokenId = oAuth2RefreshToken.getValue();
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
     * @return OAuth2RefreshToken
     */
    public OAuth2RefreshToken getoAuth2RefreshToken() {
        return oAuth2RefreshToken;
    }
    
    /**
     * 
     * @return OAuth2Authentication
     */
    public OAuth2Authentication getAuthentication() {
        return authentication;
    }
}
