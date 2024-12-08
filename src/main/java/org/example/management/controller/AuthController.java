package org.example.managment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.managment.service.AuthService;
import org.example.managment.service.JwtService;
import org.example.managment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Аутентификация")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;
    private final AuthService authService;
    private final JwtService jwtService;

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

