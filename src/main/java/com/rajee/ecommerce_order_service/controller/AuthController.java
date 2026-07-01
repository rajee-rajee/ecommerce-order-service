package com.rajee.ecommerce_order_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajee.ecommerce_order_service.dto.AuthResponse;
import com.rajee.ecommerce_order_service.dto.LoginRequest;
import com.rajee.ecommerce_order_service.dto.RegisterRequest;
import com.rajee.ecommerce_order_service.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Authentication APIs", description = "APIs for user registration and login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "register a new user", description = "This endpoint allows a new user to register by providing their details.")
    public String registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.registerUser(registerRequest);
        return "User registered successfully";
    }

    @PostMapping("/login")
    @Operation(summary = "login a user", description = "This endpoint allows a registered user to log in by providing their credentials.")
    public AuthResponse loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }
    
}

