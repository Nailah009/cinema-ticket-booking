package com.example.bookingservice.consumer;

import com.example.bookingservice.config.RabbitConfig;
import com.example.bookingservice.event.PaymentResultEvent;
import com.example.bookingservice.service.BookingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentResultConsumer {

    private final BookingService bookingService;

    public PaymentResultConsumer(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RabbitListener(queues = RabbitConfig.BOOKING_PAYMENT_SUCCESS_QUEUE)
    public void handlePaymentSuccess(PaymentResultEvent event) {
        bookingService.updateFromPaymentResult(event);
    }

    @RabbitListener(queues = RabbitConfig.BOOKING_PAYMENT_FAILED_QUEUE)
    public void handlePaymentFailed(PaymentResultEvent event) {
        bookingService.updateFromPaymentResult(event);
    }
}
