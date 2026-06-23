package com.rajee.ecommerce_order_service.entity;

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

   @NotBlank(message = "Name is mandatory")
   private String name;

   @Email(message = "Invalid email format")
   @NotBlank(message = "Email is mandatory")
   private String email;

   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   private List<CustomerOrder> orders;
    
}
