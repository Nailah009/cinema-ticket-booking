package com.example.bookingservice.producer;

import com.example.bookingservice.config.RabbitConfig;
import com.example.bookingservice.event.BookingCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public BookingEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendBookingCreated(BookingCreatedEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "booking.created", event);
    }
}
