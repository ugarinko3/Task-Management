package org.example.management.service;

import lombok.RequiredArgsConstructor;
import org.example.management.exception.JwtException;
import org.example.management.model.dto.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.example.management.exception.DuplicateEmailException;
import org.example.management.model.entity.User;
import org.example.management.model.enums.Role;
import org.example.management.model.request.UserRequest;
import org.example.management.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Регистрация
     *
     * @param token JWT
     * @return UUID пользователя
     */
    @Transactional
    public UUID registerUser(String token) {
        UserRequest userRequest = jwtService.getEmailAndPassword(token);
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateEmailException("Email уже занят.");
        }
        User user = User.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
        return user.getId();
    }

    /**
     * Авторизация
     *
     * @param token JWT
     * @return UUID пользователя
     */
    @Transactional
    public String authorization(String token) {
        try {
            UserRequest userRequest = jwtService.getEmailAndPassword(token);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword());
            authenticationManager.authenticate(authenticationToken);
            CustomUserDetails userDetails =
                    (CustomUserDetails) customUserDetailsService.loadUserByUsername(userRequest.getEmail());

            UsernamePasswordAuthenticationToken authenticatedToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticatedToken);

            return jwtService.generateToken(userRequest.getEmail(), userRequest.getPassword());
        } catch (AuthenticationException e) {
            throw new JwtException("Не удалось авторизоваться");
        }
    }
}
