package web.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Repository;
import web.domain.OAuth2AuthenticationAccessToken;
import web.domain.OAuth2AuthenticationRefreshToken;
import web.repository.interfaces.AccessTokenRepositoryInterface;
import web.repository.interfaces.RefreshTokenRepositoryInterface;

/**
 * 
 */
@Repository
public class OAuth2RepositoryTokenStore implements TokenStore {
    
    /** */
    private final AccessTokenRepositoryInterface oAuth2AccessTokenRepository;

    /** */
    private final RefreshTokenRepositoryInterface oAuth2RefreshTokenRepository;

    /** */
    private final AuthenticationKeyGenerator authenticationKeyGenerator;
    
    /**
     * 
     * @param oAuth2AccessTokenRepository
     * @param oAuth2RefreshTokenRepository 
     */
    @Autowired
    public OAuth2RepositoryTokenStore(final AccessTokenRepositoryInterface oAuth2AccessTokenRepository, final RefreshTokenRepositoryInterface oAuth2RefreshTokenRepository) {
        this.oAuth2AccessTokenRepository = oAuth2AccessTokenRepository;
        this.oAuth2RefreshTokenRepository = oAuth2RefreshTokenRepository;
        this.authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
    }
    
    /**
     * 
     * @param token
     * @return OAuth2Authentication
     */
    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }
    
    /**
     * 
     * @param tokenId
     * @return OAuth2Authentication
     */
    @Override
    public OAuth2Authentication readAuthentication(String tokenId) {
        return oAuth2AccessTokenRepository.findByTokenId(tokenId).getAuthentication();
    }
    
    /**
     * 
     * @param token
     * @param authentication 
     */
    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        String authID = authenticationKeyGenerator.extractKey(authentication);
        OAuth2AuthenticationAccessToken accessToken = new OAuth2AuthenticationAccessToken(token, authentication, authID);
        this.oAuth2AccessTokenRepository.save(accessToken);
    }

    /**
     * 
     * @param tokenValue
     * @return OAuth2AccessToken
     */
    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {

        OAuth2AuthenticationAccessToken token = this.oAuth2AccessTokenRepository.findByTokenId(tokenValue);
        OAuth2AccessToken accessToken = null;

        if (token != null) {
            accessToken = token.getoAuth2AccessToken();
        }

        return accessToken;
    }

    /**
     * 
     * @param token 
     */
    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        OAuth2AuthenticationAccessToken accessToken = this.oAuth2AccessTokenRepository.findByTokenId(token.getValue());
        if (accessToken != null) {
            this.oAuth2AccessTokenRepository.delete(accessToken);
        }
    }

    /**
     * 
     * @param refreshToken
     * @param authentication 
     */
    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        this.oAuth2RefreshTokenRepository.save(new OAuth2AuthenticationRefreshToken(refreshToken, authentication));
    }

    /**
     * 
     * @param tokenValue
     * @return 
     */
    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        return this.oAuth2RefreshTokenRepository.findByTokenId(tokenValue).getoAuth2RefreshToken();
    }

    /**
     * 
     * @param token
     * @return OAuth2Authentication
     */
    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        String tokenValue = token.getValue();
        OAuth2AuthenticationRefreshToken refreshToken = this.oAuth2RefreshTokenRepository.findByTokenId(tokenValue);
        return refreshToken.getAuthentication();
    }

    /**
     * 
     * @param refreshToken 
     */
    @Override
    public void removeRefreshToken(OAuth2RefreshToken refreshToken) {
        String tokenValue = refreshToken.getValue();
        OAuth2AuthenticationRefreshToken authRefreshToken = this.oAuth2RefreshTokenRepository.findByTokenId(tokenValue);
        this.oAuth2RefreshTokenRepository.delete(authRefreshToken);
    }

    /**
     * 
     * @param refreshToken 
     */
    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        String tokenValue = refreshToken.getValue();
        OAuth2AuthenticationAccessToken accessToken = this.oAuth2AccessTokenRepository.findByRefreshToken(tokenValue);
        this.oAuth2AccessTokenRepository.delete(accessToken);
    }

    /**
     * 
     * @param authentication
     * @return OAuth2AccessToken
     */
    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String authKey = this.authenticationKeyGenerator.extractKey(authentication);
        OAuth2AuthenticationAccessToken token = this.oAuth2AccessTokenRepository.findByAuthenticationId(authKey);
        OAuth2AccessToken accessToken = null;
        if (token != null) {
            token.getoAuth2AccessToken();
        }
        return accessToken;
    }

    /**
     * 
     * @param clientId
     * @return Collection<OAuth2AccessToken>
     */
    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<OAuth2AuthenticationAccessToken> tokens = this.oAuth2AccessTokenRepository.findByClientId(clientId);
        return extractAccessTokens(tokens);
    }

    /**
     * 
     * @param clientId
     * @param userName
     * @return Collection<OAuth2AccessToken>
     */
    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        List<OAuth2AuthenticationAccessToken> tokens = this.oAuth2AccessTokenRepository.findByClientIdAndUserName(clientId, userName);
        return extractAccessTokens(tokens);
    }

    /**
     * 
     * @param tokens
     * @return Collection<OAuth2AccessToken>
     */
    private Collection<OAuth2AccessToken> extractAccessTokens(List<OAuth2AuthenticationAccessToken> tokens) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();
        for (OAuth2AuthenticationAccessToken token : tokens) {
            accessTokens.add(token.getoAuth2AccessToken());
        }
        return accessTokens;
    }

}