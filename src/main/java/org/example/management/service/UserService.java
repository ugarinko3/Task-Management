package org.example.managment.service;

import lombok.RequiredArgsConstructor;
import org.example.managment.model.entity.User;
import org.example.managment.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

