package com.andreas.musicteacher.feature.authentication.domain;

public class RegisterUser {
    private final String email;
    private final String password;
    private final String fullName;

    public RegisterUser(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}