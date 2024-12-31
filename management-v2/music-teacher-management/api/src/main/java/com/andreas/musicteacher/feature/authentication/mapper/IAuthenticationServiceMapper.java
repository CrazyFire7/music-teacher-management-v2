package com.andreas.musicteacher.feature.authentication.mapper;

import com.andreas.musicteacher.feature.authentication.domain.LoginUser;
import com.andreas.musicteacher.feature.authentication.domain.RegisterUser;
import com.andreas.musicteacher.feature.authentication.dto.LoginUserDto;
import com.andreas.musicteacher.feature.authentication.dto.RegisterUserDto;

public interface IAuthenticationServiceMapper {
    RegisterUser mapRegisterToDomain(RegisterUserDto registerUserDto);
    LoginUser mapLoginToDomain(LoginUserDto loginUserDto);
}
