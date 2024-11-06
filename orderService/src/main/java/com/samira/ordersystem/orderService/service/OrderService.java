package com.samira.ordersystem.orderService.service;

import com.samira.ordersystem.orderService.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //mediante KafkaTemplate se envía el mensaje al topico
    public void processOrder(Order order) {
        kafkaTemplate.send("order-created", order);
    }

}
