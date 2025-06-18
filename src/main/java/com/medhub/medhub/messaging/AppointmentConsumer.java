package com.medhub.medhub.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.medhub.medhub.config.RabbitMQConfig.APPOINTMENT_QUEUE;

@Component
public class AppointmentConsumer {

    @RabbitListener(queues = APPOINTMENT_QUEUE)
    public void handleMessage(String message) {
        System.out.println("📥 Received from queue: " + message);

        // Simulate async doctor approval
        try{
            Thread.sleep(3000);  // pretend processing
            System.out.println("🩺 Doctor approved appointment ✔️");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
