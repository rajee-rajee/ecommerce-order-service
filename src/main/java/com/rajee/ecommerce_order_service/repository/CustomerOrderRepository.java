package com.rajee.ecommerce_order_service.repository;

import com.rajee.ecommerce_order_service.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    
}
