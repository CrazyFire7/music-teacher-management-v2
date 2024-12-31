package com.andreas.musicteacher.feature.authentication.mapper;

import com.andreas.musicteacher.feature.authentication.domain.LoginUser;
import com.andreas.musicteacher.feature.authentication.domain.RegisterUser;
import com.andreas.musicteacher.feature.authentication.dto.LoginUserDto;
import com.andreas.musicteacher.feature.authentication.dto.RegisterUserDto;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceMapper implements IAuthenticationServiceMapper {
    @Override
    public RegisterUser mapRegisterToDomain(RegisterUserDto registerUserDto) {
        var registerUser = new RegisterUser(registerUserDto.getEmail(), registerUserDto.getPassword(), registerUserDto.getFullName());

        return registerUser;
    }

    @Override
    public LoginUser mapLoginToDomain(LoginUserDto loginUserDto) {
        var loginUser = new LoginUser(loginUserDto.getEmail(), loginUserDto.getPassword());

        return loginUser;
    }
}
