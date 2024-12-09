package org.example.management.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.management.model.entity.User;
import org.example.management.model.enums.Role;
import org.example.management.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Сменить {@link Role} пользователю с<br>
     * {@link Role ROLE_USER} на {@link Role ROLE_ADMIN}
     */
    @Transactional
    public void changeUserToAdmin() {
        User userDetails = customUserDetailsService.getCurrentUser();
        userDetails.setRole(Role.ROLE_ADMIN);
        userRepository.save(userDetails);
        setRoleUser();
    }

    private void setRoleUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> updateAuth = new ArrayList<>(authentication.getAuthorities());
        updateAuth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), updateAuth);
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }
}
