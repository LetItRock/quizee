package com.grapeup.authserver.repository;

import com.grapeup.authserver.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pavlo Tymchuk
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
