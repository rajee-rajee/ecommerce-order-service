package com.rajee.ecommerce_order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajee.ecommerce_order_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
