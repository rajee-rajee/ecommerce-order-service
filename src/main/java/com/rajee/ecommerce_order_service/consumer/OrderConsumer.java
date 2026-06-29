package com.rajee.ecommerce_order_service.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rajee.ecommerce_order_service.dto.OrderCreatedEvent;

@Service
public class OrderConsumer {

     private static final Logger log =
        LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {

        System.out.println("====================================");
        log.info("Received OrderCreatedEvent");

        log.info("Order Id : {}", event.getOrderId());

        log.info("User Id : {}", event.getUserId());

        log.info("Sending Email");

        log.info("Email sent successfully");
        System.out.println("====================================");
    }

}