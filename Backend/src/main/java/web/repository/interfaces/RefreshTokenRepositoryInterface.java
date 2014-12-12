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
     * Finds the refresh token based on the ID.
     * 
     * @param tokenId - The ID of the token to find.
     * @return OAuth2AuthenticationRefreshToken - The token or null if not found.
     */
    public OAuth2AuthenticationRefreshToken findByTokenId(String tokenId);
}
