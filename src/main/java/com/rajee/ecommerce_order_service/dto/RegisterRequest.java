package com.rajee.ecommerce_order_service.dto;

import com.rajee.ecommerce_order_service.entity.Role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;


@Getter
@Setter
public class RegisterRequest {

       @NotBlank(message = "Name is required")
        private String name;
    
        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        @Column(unique = true)
        private String email;
    
        @NotBlank(message = "Password is required")
        private String password;
    
        @Enumerated(EnumType.STRING)
        private Role role;
}
