package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.management.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AdminService adminService;

    @PutMapping("/change-user-to-admin")
    @Operation(summary = "Изменение роли на ROLE_ADMIN")
    public ResponseEntity<Void> changeUserToAdmin() {
        adminService.changeUserToAdmin();
        return ResponseEntity.ok().build();
    }
}
