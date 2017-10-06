package com.grapeup.authserver.repository;

import com.grapeup.authserver.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pavlo Tymchuk
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
