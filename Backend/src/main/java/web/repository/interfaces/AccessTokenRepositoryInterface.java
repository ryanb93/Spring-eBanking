package web.repository.interfaces;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.domain.OAuth2AuthenticationAccessToken;

/**
 * The interface for the access token repository.
 * 
 * Based on the work of: https://github.com/iainporter/oauth2-provider/
 */
@Repository
public interface AccessTokenRepositoryInterface extends MongoRepository<OAuth2AuthenticationAccessToken, String> {

    /**
     * Method to find an authentication token by its ID.
     * 
     * @param tokenId - The ID of the token.
     * @return OAuth2AuthenticationAccessToken -  The authentication access token.
     */
    public OAuth2AuthenticationAccessToken findByTokenId(String tokenId);

    /**
     * Method to find an authentication token by its refresh token.
     * 
     * @param refreshToken - The refresh token linked to the authentication token.
     * @return OAuth2AuthenticationAccessToken -  The authentication access token.
     */
    public OAuth2AuthenticationAccessToken findByRefreshToken(String refreshToken);

    /**
     * Method to find an authentication token by its authentication ID.
     * 
     * @param authenticationId - The authentication ID.
     * @return OAuth2AuthenticationAccessToken - The authentication access token.
     */
    public OAuth2AuthenticationAccessToken findByAuthenticationId(String authenticationId);

    /**
     * Method to find all authentication tokens linked to a client id and user name..
     * 
     * @param clientId - The ID of the client.
     * @param userName - The username.
     * @return List<OAuth2AuthenticationAccessToken> - A list of all the tokens.
     */
    public List<OAuth2AuthenticationAccessToken> findByClientIdAndUserName(String clientId, String userName);

    /**
     * Method to find all authentication tokens linked to a client id.
     * 
     * @param clientId - The ID of the client.
     * @return List<OAuth2AuthenticationAccessToken> - A list of all the tokens.
     */
    public List<OAuth2AuthenticationAccessToken> findByClientId(String clientId);
}
