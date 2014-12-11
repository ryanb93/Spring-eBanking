package web.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.domain.OAuth2AuthenticationAccessToken;

/**
 * 
 */
@Repository
public interface OAuth2AccessTokenRepository extends MongoRepository<OAuth2AuthenticationAccessToken, String> {

    /**
     * 
     * @param tokenId
     * @return OAuth2AuthenticationAccessToken
     */
    public OAuth2AuthenticationAccessToken findByTokenId(String tokenId);

    /**
     * 
     * @param refreshToken
     * @return OAuth2AuthenticationAccessToken
     */
    public OAuth2AuthenticationAccessToken findByRefreshToken(String refreshToken);

    /**
     * 
     * @param authenticationId
     * @return OAuth2AuthenticationAccessToken
     */
    public OAuth2AuthenticationAccessToken findByAuthenticationId(String authenticationId);

    /**
     * 
     * @param clientId
     * @param userName
     * @return List<OAuth2AuthenticationAccessToken>
     */
    public List<OAuth2AuthenticationAccessToken> findByClientIdAndUserName(String clientId, String userName);

    /**
     * 
     * @param clientId
     * @return List<OAuth2AuthenticationAccessToken>
     */
    public List<OAuth2AuthenticationAccessToken> findByClientId(String clientId);
}
