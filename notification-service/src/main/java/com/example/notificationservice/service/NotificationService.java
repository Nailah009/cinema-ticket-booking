package com.example.notificationservice.service;

import com.example.notificationservice.entity.NotificationLog;
import com.example.notificationservice.event.PaymentResultEvent;
import com.example.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void savePaymentNotification(PaymentResultEvent event) {
        NotificationLog log = new NotificationLog();
        log.setId(UUID.randomUUID().toString());
        log.setBookingId(event.getBookingId());
        log.setRecipient(event.getEmail());
        log.setCreatedAt(LocalDateTime.now());

        if ("SUCCESS".equalsIgnoreCase(event.getPaymentStatus())) {
            log.setType("E_TICKET");
            log.setStatus("SENT");
            log.setMessage("Hi " + event.getCustomerName() + ", booking " + event.getBookingId()
                    + " berhasil dibayar. E-ticket sudah dikirim.");
        } else {
            log.setType("PAYMENT_FAILED");
            log.setStatus("SENT");
            log.setMessage("Hi " + event.getCustomerName() + ", pembayaran booking " + event.getBookingId()
                    + " gagal. Reason: " + event.getReason());
        }

        notificationRepository.save(log);
    }

    public List<NotificationLog> findAll() {
        return notificationRepository.findAll();
    }

    public List<NotificationLog> findByBookingId(String bookingId) {
        return notificationRepository.findByBookingId(bookingId);
    }
}
