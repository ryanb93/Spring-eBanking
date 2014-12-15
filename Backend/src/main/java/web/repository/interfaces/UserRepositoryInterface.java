package web.repository.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.domain.APIUser;

/**
 * The APIUser Repository Interface
 */
@Repository
public interface UserRepositoryInterface extends MongoRepository<APIUser, String> {

    /**
     * Method to retrieve a APIUser by their Email Address
     * @param name the Email Address to find the user by
     * @return APIUser the APIUser who has the requested Email Address
     */
    public APIUser findByEmailAddress(final String name);

    /**
     * Method to retrieve a APIUser from their ID
     * @param id the ID to find the APIUser by
     * @return APIUser the APIUser who has the requested ID
     */
    public APIUser findById(final String id);
}
