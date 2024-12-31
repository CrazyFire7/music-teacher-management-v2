package com.andreas.musicteacher.feature.authentication.controller;

import com.andreas.musicteacher.feature.authentication.domain.IAuthenticationDomain;
import com.andreas.musicteacher.feature.authentication.dto.LoginResponseDto;
import com.andreas.musicteacher.feature.authentication.dto.LoginUserDto;
import com.andreas.musicteacher.feature.authentication.dto.RegisterUserDto;
import com.andreas.musicteacher.feature.authentication.mapper.IAuthenticationServiceMapper;
import com.andreas.musicteacher.shared.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationController {
    private final JwtService jwtService;
    private final IAuthenticationDomain domain;
    private final IAuthenticationServiceMapper mapper;

    public AuthenticationController(JwtService jwtService, IAuthenticationDomain domain, IAuthenticationServiceMapper mapper) {
        this.jwtService = jwtService;
        this.domain = domain;
        this.mapper = mapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> register(@RequestBody RegisterUserDto registerUserDto) {
        var registerUser = mapper.mapRegisterToDomain(registerUserDto);

        domain.signUp(registerUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        var loginUser = mapper.mapLoginToDomain(loginUserDto);

        var userDetails = domain.authenticate(loginUser);

        var jwtToken = jwtService.generateToken(userDetails);

        var loginResponseDto = new LoginResponseDto("Login successful")
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponseDto);
    }

    @GetMapping("/check-auth")
    public ResponseEntity<String> checkAuth() {
        return ResponseEntity.ok("User is authenticated");
    }
}

