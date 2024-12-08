package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.management.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Аутентификация")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Регистрация")
    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestHeader(value = "token", required = false) String token)  {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.registerUser(token));
    }

    @GetMapping("/admin")
    public String admin() {
        return "Succes";
    }
    @GetMapping("/user")
    public String user() {
        return "Succes";
    }
}

