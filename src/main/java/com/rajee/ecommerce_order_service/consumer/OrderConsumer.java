package com.rajee.ecommerce_order_service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rajee.ecommerce_order_service.dto.OrderCreatedEvent;

@Service
public class OrderConsumer {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {

        System.out.println("====================================");
        System.out.println("Order Event Received");
        System.out.println("Order Id : " + event.getOrderId());
        System.out.println("User Id  : " + event.getUserId());
        System.out.println("Message  : " + event.getMessage());
        System.out.println("Sending Email...");
        System.out.println("Email Sent Successfully");
        System.out.println("====================================");
    }

}