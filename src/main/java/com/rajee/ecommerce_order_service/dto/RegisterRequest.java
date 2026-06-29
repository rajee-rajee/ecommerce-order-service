package com.rajee.ecommerce_order_service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


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
}
