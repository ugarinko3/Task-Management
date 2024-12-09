package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.management.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Аутентификация")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Регистрация")
    @PostMapping("/registration")
    public ResponseEntity<UUID> register(@RequestHeader(value = "token") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(token));
    }

    @Operation(summary = "Авторизация")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader(value = "token") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.authorization(token));
    }
}
