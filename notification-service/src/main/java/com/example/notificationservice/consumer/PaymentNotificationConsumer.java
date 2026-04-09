package com.example.notificationservice.consumer;

import com.example.notificationservice.config.RabbitConfig;
import com.example.notificationservice.event.PaymentResultEvent;
import com.example.notificationservice.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentNotificationConsumer {

    private final NotificationService notificationService;

    public PaymentNotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitConfig.PAYMENT_SUCCESS_QUEUE)
    public void onPaymentSuccess(PaymentResultEvent event) {
        notificationService.savePaymentNotification(event);
    }

    @RabbitListener(queues = RabbitConfig.PAYMENT_FAILED_QUEUE)
    public void onPaymentFailed(PaymentResultEvent event) {
        notificationService.savePaymentNotification(event);
    }
}
