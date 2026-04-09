package com.example.paymentservice.producer;

import com.example.paymentservice.config.RabbitConfig;
import com.example.paymentservice.event.PaymentResultEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentResultProducer {

    private final RabbitTemplate rabbitTemplate;

    public PaymentResultProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendSuccess(PaymentResultEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "payment.success", event);
    }

    public void sendFailure(PaymentResultEvent event) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "payment.failed", event);
    }
}
