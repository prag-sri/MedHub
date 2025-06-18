package com.medhub.medhub.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventConsumer {

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message){
        System.out.println("📥 Received Kafka event: " + message);
        // TODO: Save to audit log table or log file
    }
}
