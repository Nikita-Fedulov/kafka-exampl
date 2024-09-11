package com.example.kafka_producer.listener;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderStatusListener {

    @KafkaListener(topics = "${app.kafka.kafkaMessageTopic}", groupId = "${app.kafka.kafkaMessageGroupId}", containerFactory = "kafkaListenerContainerFactory")
    public void listenOrderStatus(ConsumerRecord<String, String> record) {
        // Выводим информацию о полученном сообщении
        log.info("Received message: {}", record.value());
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}",
                record.key(), record.partition(), record.topic(), record.timestamp());
    }


}
