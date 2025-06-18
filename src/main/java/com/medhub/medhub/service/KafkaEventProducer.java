package com.medhub.medhub.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    public KafkaEventProducer(KafkaTemplate<String,String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String message) {
        kafkaTemplate.send(topic,message);
        System.out.println("📤 Sent Kafka Event: " + message);
    }
}
