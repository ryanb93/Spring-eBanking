package web.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmailAddress(final String name);

    public User findById(final String id);
}
