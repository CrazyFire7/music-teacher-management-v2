package com.andreas.musicteacher.feature.authentication.repository;

import com.andreas.musicteacher.feature.authentication.domain.IUserJpaRepository;
import com.andreas.musicteacher.feature.authentication.domain.RegisterUser;
import com.andreas.musicteacher.shared.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class UserJpaRepository implements IUserJpaRepository {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserJpaRepositoryMapper userJpaRepositoryMapper;

    public UserJpaRepository(UserRepository userRepository, PasswordEncoder passwordEncoder, IUserJpaRepositoryMapper userJpaRepositoryMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userJpaRepositoryMapper = userJpaRepositoryMapper;
    }

    @Override
    public void save(RegisterUser registerUser) {
        var user = userJpaRepositoryMapper.mapToEntity(registerUser);
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));

        userRepository.save(user);
    }

    @Override
    public UserDetails findByEmail(String email) {
        var user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        var userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }
        };

        return userDetails;
    }
}
