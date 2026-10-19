package kz.students.taskmanager.controller;

import jakarta.validation.Valid;
import kz.students.taskmanager.dto.AuthResponse;
import kz.students.taskmanager.dto.LoginRequest;
import kz.students.taskmanager.dto.UserRequest;
import kz.students.taskmanager.dto.UserResponse;
import kz.students.taskmanager.service.AuthService;
import kz.students.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRequest request) {
        return userService.create(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
