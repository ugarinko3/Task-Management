package org.example.management.service;

import lombok.RequiredArgsConstructor;
import org.example.management.exception.DuplicateEmailException;
import org.example.management.model.entity.User;
import org.example.management.model.enums.Role;
import org.example.management.model.request.UserRequest;
import org.example.management.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(String token) {
        UserRequest userRequest = jwtService.getEmailAndPassword(token);
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateEmailException("Email уже занят.");
        }
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return HttpStatus.CREATED.getReasonPhrase();
    }
}

