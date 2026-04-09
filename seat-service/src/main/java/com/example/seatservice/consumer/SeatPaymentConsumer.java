package com.example.seatservice.consumer;

import com.example.seatservice.config.RabbitConfig;
import com.example.seatservice.event.PaymentResultEvent;
import com.example.seatservice.service.SeatService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SeatPaymentConsumer {

    private final SeatService seatService;

    public SeatPaymentConsumer(SeatService seatService) {
        this.seatService = seatService;
    }

    @RabbitListener(queues = RabbitConfig.PAYMENT_SUCCESS_QUEUE)
    public void handlePaymentSuccess(PaymentResultEvent event) {
        seatService.confirmSeats(event);
    }

    @RabbitListener(queues = RabbitConfig.PAYMENT_FAILED_QUEUE)
    public void handlePaymentFailed(PaymentResultEvent event) {
        seatService.releaseSeats(event);
    }
}
