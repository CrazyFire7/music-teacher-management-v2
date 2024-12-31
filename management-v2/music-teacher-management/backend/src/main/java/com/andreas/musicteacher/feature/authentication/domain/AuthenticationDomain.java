package com.andreas.musicteacher.feature.authentication.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
class AuthenticationDomain implements CommandLineRunner, IAuthenticationDomain {
    private final IUserJpaRepository userJpaRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationDomain(
            IUserJpaRepository userJpaRepository,
            AuthenticationManager authenticationManager
    ) {
        this.userJpaRepository = userJpaRepository;
        this.authenticationManager = authenticationManager;
    }

    public void signUp(RegisterUser registerUser) {
        userJpaRepository.save(registerUser);
    }

    public UserDetails authenticate(LoginUser loginUser) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );

        return userJpaRepository.findByEmail(loginUser.getEmail());
    }

    @Override
    public void run(String... args) {
        if (userJpaRepository.findByEmail("admin@admin.com") == null) {
            RegisterUser adminUser = new RegisterUser("admin@admin.com", "admin", "Admin User");

            userJpaRepository.save(adminUser);
            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
