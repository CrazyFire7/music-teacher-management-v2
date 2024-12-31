package com.andreas.musicteacher.feature.authentication.repository;

import org.springframework.data.repository.CrudRepository;
import com.andreas.musicteacher.shared.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
