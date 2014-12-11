package web.repository.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.domain.User;

/**
 * The User Repository Interface
 */
@Repository
public interface UserRepositoryInterface extends MongoRepository<User, String> {

    /**
     * Method to retrieve a User by their Email Address
     * @param name the Email Address to find the user by
     * @return User the User who has the requested Email Address
     */
    public User findByEmailAddress(final String name);

    /**
     * Method to retrieve a User from their ID
     * @param id the ID to find the User by
     * @return User the User who has the requested ID
     */
    public User findById(final String id);
}
