package com.rajee.ecommerce_order_service.service;

import com.rajee.ecommerce_order_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.rajee.ecommerce_order_service.entity.User;
import com.rajee.ecommerce_order_service.dto.RegisterRequest;

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    
}
