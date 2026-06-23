package com.rajee.ecommerce_order_service.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String email;

   @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   private List<CustomerOrder> orders;
    
}
