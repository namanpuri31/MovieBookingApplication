package com.MovieBookingSystem.MovieBookingSystem.Producer;

import com.MovieBookingSystem.MovieBookingSystem.Configuration.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public <T> void receiveBooking(T request) {

            amqpTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY,
                    request
            );
            System.out.println("📤 Sent BookingRequest: " + request);
    }
}
