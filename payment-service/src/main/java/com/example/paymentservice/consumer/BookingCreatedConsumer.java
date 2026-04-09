package com.example.paymentservice.consumer;

import com.example.paymentservice.config.RabbitConfig;
import com.example.paymentservice.event.BookingCreatedEvent;
import com.example.paymentservice.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingCreatedConsumer {

    private final PaymentService paymentService;

    public BookingCreatedConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RabbitListener(queues = RabbitConfig.BOOKING_CREATED_QUEUE)
    public void handleBookingCreated(BookingCreatedEvent event) {
        paymentService.processBookingPayment(event);
    }
}
