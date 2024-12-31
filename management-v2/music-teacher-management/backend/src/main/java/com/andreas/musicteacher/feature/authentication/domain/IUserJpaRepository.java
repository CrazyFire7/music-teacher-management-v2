package com.andreas.musicteacher.feature.authentication.domain;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserJpaRepository {
    void save(RegisterUser registerUser);

    UserDetails findByEmail(String email);
}
