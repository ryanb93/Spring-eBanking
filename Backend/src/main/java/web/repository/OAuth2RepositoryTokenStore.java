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
 * An implementation of a token store, allows us to store OAuth2 tokens using Mongo.
 */
@Repository
public class OAuth2RepositoryTokenStore implements TokenStore {
    
    /** A reference to the AccessToken repository.*/
    private final AccessTokenRepositoryInterface oAuth2AccessTokenRepository;

    /** A reference to the RefreshToken repository.*/
    private final RefreshTokenRepositoryInterface oAuth2RefreshTokenRepository;

    /** A reference to the AuthenticationKeyGenerator.*/
    private final AuthenticationKeyGenerator authenticationKeyGenerator;
    
    /**
     * Constructor for the token store. Sets up the different repositories.
     * 
     * @param oAuth2AccessTokenRepository - The OAuth2 AccessToken repo.
     * @param oAuth2RefreshTokenRepository - The OAuth2 RefreshToken repo.
     */
    @Autowired
    public OAuth2RepositoryTokenStore(final AccessTokenRepositoryInterface oAuth2AccessTokenRepository, final RefreshTokenRepositoryInterface oAuth2RefreshTokenRepository) {
        this.oAuth2AccessTokenRepository = oAuth2AccessTokenRepository;
        this.oAuth2RefreshTokenRepository = oAuth2RefreshTokenRepository;
        this.authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
    }
    
    /**
     * Read the authentication stored under the specified token value.
     * 
     * @param token - Token to get value from.
     * @return OAuth2Authentication - The authentication object.
     */
    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }
    
    /**
     * Read the authentication stored under the specified token value.
     * 
     * @param tokenId - Token ID to get value from.
     * @return OAuth2Authentication - The authentication object.
     */
    @Override
    public OAuth2Authentication readAuthentication(String tokenId) {
        return oAuth2AccessTokenRepository.findByTokenId(tokenId).getAuthentication();
    }
    
    /**
     * Store an access token in the database.
     * 
     * @param token - The token to store.
     * @param authentication - The authentication to store with it.
     */
    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        //extract the auth key from the auth object.
        String authID = authenticationKeyGenerator.extractKey(authentication);
        //Create an access token using the existing token, authentication object and auth key.
        OAuth2AuthenticationAccessToken accessToken = new OAuth2AuthenticationAccessToken(token, authentication, authID);
        //Save the token to the repo.
        this.oAuth2AccessTokenRepository.save(accessToken);
    }

    /**
     * Read an access token.
     * 
     * @param tokenValue - The token ID.
     * @return OAuth2AccessToken - The access token.
     */
    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        //Get the auth token from the database.
        OAuth2AuthenticationAccessToken token = this.oAuth2AccessTokenRepository.findByTokenId(tokenValue);
        //Create an empty access token.
        OAuth2AccessToken accessToken = null;
        //If the auth token existed from the token value given.
        if (token != null) {
            //Get the auth access token from the database token.
            accessToken = token.getoAuth2AccessToken();
        }
        return accessToken;
    }

    /**
     * Removes an access token from the repository.
     * 
     * @param token The token to remove.
     */
    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        OAuth2AuthenticationAccessToken accessToken = this.oAuth2AccessTokenRepository.findByTokenId(token.getValue());
        if (accessToken != null) {
            this.oAuth2AccessTokenRepository.delete(accessToken);
        }
    }

    /**
     * Stores a refresh token in the repository.
     * 
     * @param refreshToken - The token to store.
     * @param authentication - The authentication object.
     */
    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        this.oAuth2RefreshTokenRepository.save(new OAuth2AuthenticationRefreshToken(refreshToken, authentication));
    }

    /**
     * Reads a refresh token from the repository.
     * 
     * @param tokenValue The token ID.
     * @return The refresh token object.
     */
    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        return this.oAuth2RefreshTokenRepository.findByTokenId(tokenValue).getoAuth2RefreshToken();
    }

    /**
     * Reads the authentication object from the refresh token given.
     * 
     * @param token - The token to get the authentication object for.
     * @return OAuth2Authentication - The authentication object.
     */
    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        String tokenValue = token.getValue();
        OAuth2AuthenticationRefreshToken refreshToken = this.oAuth2RefreshTokenRepository.findByTokenId(tokenValue);
        return refreshToken.getAuthentication();
    }

    /**
     * Removes a refresh token from the repository.
     * @param refreshToken The refresh token to remove.
     */
    @Override
    public void removeRefreshToken(OAuth2RefreshToken refreshToken) {
        String tokenValue = refreshToken.getValue();
        OAuth2AuthenticationRefreshToken authRefreshToken = this.oAuth2RefreshTokenRepository.findByTokenId(tokenValue);
        this.oAuth2RefreshTokenRepository.delete(authRefreshToken);
    }

    /**
     * Removes an access token from the database using a refresh token.
     * 
     * @param refreshToken The refresh token.
     */
    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        String tokenValue = refreshToken.getValue();
        OAuth2AuthenticationAccessToken accessToken = this.oAuth2AccessTokenRepository.findByRefreshToken(tokenValue);
        this.oAuth2AccessTokenRepository.delete(accessToken);
    }

    /**
     * Get the access token from the repository.
     * 
     * @param authentication The authentication object.
     * @return OAuth2AccessToken The access token that is linked to that authentication object.
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
     * Finds all tokens based on the client ID.
     * 
     * @param clientId - The client ID.
     * @return Collection<OAuth2AccessToken> - All the tokens linked to that client.
     */
    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<OAuth2AuthenticationAccessToken> tokens = this.oAuth2AccessTokenRepository.findByClientId(clientId);
        return extractAccessTokens(tokens);
    }

    /**
     * Finds tokens based on the client ID and the name of the user.
     * 
     * @param clientId - The client ID.
     * @param userName - The username of the user.
     * @return Collection<OAuth2AccessToken> - All the tokens linked to that client and user.
     */
    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        List<OAuth2AuthenticationAccessToken> tokens = this.oAuth2AccessTokenRepository.findByClientIdAndUserName(clientId, userName);
        return extractAccessTokens(tokens);
    }

    /**
     * Extracts all the access tokens from a list of authentication tokens.
     * 
     * @param tokens - The authentication tokens to extract.
     * @return Collection<OAuth2AccessToken> - The extracted tokens.
     */
    private Collection<OAuth2AccessToken> extractAccessTokens(List<OAuth2AuthenticationAccessToken> tokens) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();
        for (OAuth2AuthenticationAccessToken token : tokens) {
            accessTokens.add(token.getoAuth2AccessToken());
        }
        return accessTokens;
    }

}
