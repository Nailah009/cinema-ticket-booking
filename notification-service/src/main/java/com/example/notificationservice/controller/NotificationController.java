package com.example.notificationservice.controller;

import com.example.notificationservice.entity.NotificationLog;
import com.example.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationLog> getAll() {
        return notificationService.findAll();
    }

    @GetMapping("/booking/{bookingId}")
    public List<NotificationLog> getByBookingId(@PathVariable String bookingId) {
        return notificationService.findByBookingId(bookingId);
    }
}
