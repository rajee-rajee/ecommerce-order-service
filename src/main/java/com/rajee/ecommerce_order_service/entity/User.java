package com.rajee.ecommerce_order_service.entity;

import com.rajee.ecommerce_order_service.entity.Role;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

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

   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   private List<CustomerOrder> orders;
    
}
