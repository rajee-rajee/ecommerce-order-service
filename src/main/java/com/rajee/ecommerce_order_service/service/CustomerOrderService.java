package com.rajee.ecommerce_order_service.service;

import org.springframework.stereotype.Service;

import com.rajee.ecommerce_order_service.dto.OrderCreatedEvent;
import com.rajee.ecommerce_order_service.entity.CustomerOrder;
import com.rajee.ecommerce_order_service.entity.User;
import com.rajee.ecommerce_order_service.producer.OrderProducer;
import com.rajee.ecommerce_order_service.repository.CustomerOrderRepository;
import com.rajee.ecommerce_order_service.repository.UserRepository;

@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final OrderProducer orderProducer;
    private final UserRepository userRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, OrderProducer orderProducer, UserRepository userRepository) {
        this.customerOrderRepository = customerOrderRepository;
        this.orderProducer = orderProducer;
        this.userRepository = userRepository;
    }

    public CustomerOrder createCustomerOrder(CustomerOrder order) {

        if (order.getUser() != null && order.getUser().getId() != null) {
            User user = userRepository.findById(order.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + order.getUser().getId()));
            order.setUser(user);
        }

        CustomerOrder savedOrder = customerOrderRepository.save(order);

        OrderCreatedEvent event =
                new OrderCreatedEvent(
                        savedOrder.getId(),
                        savedOrder.getUser().getId(),
                        "Order Created Successfully");

        orderProducer.sendOrderCreatedEvent(event);

        return savedOrder;
    }

    public CustomerOrder getCustomerOrderById(Long orderId) {
        return customerOrderRepository.findById(orderId).orElseThrow();
    }
    
}
