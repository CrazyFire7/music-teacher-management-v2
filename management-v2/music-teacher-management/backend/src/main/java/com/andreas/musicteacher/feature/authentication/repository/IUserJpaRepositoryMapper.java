package com.andreas.musicteacher.feature.authentication.repository;

import com.andreas.musicteacher.feature.authentication.domain.RegisterUser;
import com.andreas.musicteacher.shared.model.User;

public interface IUserJpaRepositoryMapper {
    User mapToEntity(RegisterUser registerUser);
}
