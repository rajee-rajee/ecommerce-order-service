package com.rajee.ecommerce_order_service.service;

import com.rajee.ecommerce_order_service.entity.CustomerOrder;
import com.rajee.ecommerce_order_service.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder) {
        return customerOrderRepository.save(customerOrder);
    }

    public CustomerOrder getCustomerOrderById(Long orderId) {
        return customerOrderRepository.findById(orderId).orElseThrow();
    }
    
}
