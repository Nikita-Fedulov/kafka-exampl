package com.example.kafka_producer.controller;

import com.example.kafka_producer.model.Order;
import com.example.kafka_producer.model.OrderEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Value("${app.kafka.kafkaMessageTopic}")
    private String orderTopic;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderController(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<String> creteOrder(@RequestBody Order order){
        OrderEvent orderEvent = new OrderEvent(order.getProduct(), order.getQuantity());
        kafkaTemplate.send(orderTopic, orderEvent);
        return ResponseEntity.ok("Order sent to Kafka");
    }
}
