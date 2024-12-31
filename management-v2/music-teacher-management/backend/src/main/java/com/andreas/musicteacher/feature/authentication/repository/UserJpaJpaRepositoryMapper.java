package com.andreas.musicteacher.feature.authentication.repository;

import com.andreas.musicteacher.feature.authentication.domain.RegisterUser;
import com.andreas.musicteacher.shared.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserJpaJpaRepositoryMapper implements IUserJpaRepositoryMapper {
    @Override
    public User mapToEntity(RegisterUser registerUser) {
        var user = new User()
                .setFullName(registerUser.getFullName())
                .setUsername(registerUser.getEmail())
                .setPassword(registerUser.getPassword());

        return user;
    }
}
