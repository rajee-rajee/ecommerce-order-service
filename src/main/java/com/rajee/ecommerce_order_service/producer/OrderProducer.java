package com.rajee.ecommerce_order_service.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rajee.ecommerce_order_service.dto.OrderCreatedEvent;

@Service
public class OrderProducer {

    private static final Logger log =
        LoggerFactory.getLogger(OrderProducer.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {

        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                event);

        log.info("Published OrderCreatedEvent for order {}", event.getOrderId());
    }

}