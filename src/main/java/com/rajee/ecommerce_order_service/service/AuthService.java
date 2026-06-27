package com.rajee.ecommerce_order_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rajee.ecommerce_order_service.dto.AuthResponse;
import com.rajee.ecommerce_order_service.dto.LoginRequest;
import com.rajee.ecommerce_order_service.dto.RegisterRequest;
import com.rajee.ecommerce_order_service.entity.User;
import com.rajee.ecommerce_order_service.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void registerUser(RegisterRequest register) {
        if(userRepository.existsByEmail(register.getEmail())) {
            throw new RuntimeException("User with email " + register.getEmail() + " already exists.");
        }

        User user = new User();
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        
        userRepository.save(user);

    }

    public AuthResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User with email " + request.getEmail() + " not found."));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password for user with email " + request.getEmail());
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
    
}
