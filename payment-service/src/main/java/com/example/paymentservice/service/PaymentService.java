package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.entity.PaymentStatus;
import com.example.paymentservice.event.BookingCreatedEvent;
import com.example.paymentservice.event.PaymentResultEvent;
import com.example.paymentservice.producer.PaymentResultProducer;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentResultProducer paymentResultProducer;

    public PaymentService(PaymentRepository paymentRepository, PaymentResultProducer paymentResultProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentResultProducer = paymentResultProducer;
    }

    public void processBookingPayment(BookingCreatedEvent event) {
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setBookingId(event.getBookingId());
        payment.setAmount(event.getTotalPrice());
        payment.setPaymentMethod(event.getPaymentMethod());
        payment.setCreatedAt(LocalDateTime.now());

        boolean success = !"FAIL".equalsIgnoreCase(event.getPaymentMethod());

        if (success) {
            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setReason("Payment completed");
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setReason("Payment was simulated as FAILED because paymentMethod=FAIL");
        }

        paymentRepository.save(payment);

        PaymentResultEvent resultEvent = new PaymentResultEvent();
        resultEvent.setBookingId(event.getBookingId());
        resultEvent.setScheduleId(event.getScheduleId());
        resultEvent.setSeats(event.getSeats());
        resultEvent.setAmount(event.getTotalPrice());
        resultEvent.setPaymentMethod(event.getPaymentMethod());
        resultEvent.setEmail(event.getEmail());
        resultEvent.setCustomerName(event.getCustomerName());
        resultEvent.setPaymentStatus(payment.getStatus().name());
        resultEvent.setReason(payment.getReason());

        if (success) {
            paymentResultProducer.sendSuccess(resultEvent);
        } else {
            paymentResultProducer.sendFailure(resultEvent);
        }
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> findByBookingId(String bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
}
