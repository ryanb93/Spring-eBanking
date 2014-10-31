package web.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmailAddress(final String name);

    public User findById(final String id);
}
