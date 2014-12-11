package web.repository.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.domain.User;

/**
 * 
 */
@Repository
public interface UserRepositoryInterface extends MongoRepository<User, String> {

    /**
     * 
     * @param name
     * @return User
     */
    public User findByEmailAddress(final String name);

    /**
     * 
     * @param id
     * @return User
     */
    public User findById(final String id);
}
