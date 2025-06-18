package com.medhub.medhub.messaging;

import com.medhub.medhub.entity.Appointment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.medhub.medhub.config.RabbitMQConfig.APPOINTMENT_QUEUE;

@Service
public class AppointmentProducer {

    private final RabbitTemplate rabbitTemplate;

    public AppointmentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(APPOINTMENT_QUEUE, message);
        System.out.println("📤 Sent message to queue: " + message);
    }
}
