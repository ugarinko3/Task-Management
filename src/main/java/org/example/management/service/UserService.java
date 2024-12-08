package org.example.management.service;

import lombok.RequiredArgsConstructor;
import org.example.management.model.entity.User;
import org.example.management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

