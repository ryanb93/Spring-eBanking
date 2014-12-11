package web.repository.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.domain.OAuth2AuthenticationRefreshToken;

/**
 * 
 */
@Repository
public interface RefreshTokenRepositoryInterface extends MongoRepository<OAuth2AuthenticationRefreshToken, String> {

    /**
     * 
     * @param tokenId
     * @return OAuth2AuthenticationRefreshToken
     */
    public OAuth2AuthenticationRefreshToken findByTokenId(String tokenId);
}
