package org.example.management.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Используем лямбда-выражение для конфигурации
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()  // Разрешить доступ всем к /auth/**
                        .requestMatchers("/admin/**").hasRole("0")  // Доступ для админов
                        .requestMatchers("/user/**").hasRole("USER")  // Доступ для обычных пользователей
                        .anyRequest().authenticated()  // Все остальные запросы требуют авторизации
                );

        return http.build();
    }
}