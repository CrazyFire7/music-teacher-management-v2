package com.andreas.musicteacher.feature.authentication.domain;

import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationDomain {
    void signUp(RegisterUser registerUser);

    UserDetails authenticate(LoginUser loginUser);
}
