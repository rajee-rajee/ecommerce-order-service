package com.rajee.ecommerce_order_service.service;

import org.springframework.stereotype.Service;

import com.rajee.ecommerce_order_service.dto.OrderCreatedEvent;
import com.rajee.ecommerce_order_service.entity.CustomerOrder;
import com.rajee.ecommerce_order_service.producer.OrderProducer;
import com.rajee.ecommerce_order_service.repository.CustomerOrderRepository;

@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final OrderProducer orderProducer;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, OrderProducer orderProducer) {
        this.customerOrderRepository = customerOrderRepository;
        this.orderProducer = orderProducer;
    }

    public CustomerOrder createCustomerOrder(CustomerOrder order) {

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
