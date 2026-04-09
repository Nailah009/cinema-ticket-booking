package com.example.bookingservice.service;

import com.example.bookingservice.dto.*;
import com.example.bookingservice.entity.Booking;
import com.example.bookingservice.entity.BookingStatus;
import com.example.bookingservice.event.BookingCreatedEvent;
import com.example.bookingservice.event.PaymentResultEvent;
import com.example.bookingservice.producer.BookingEventProducer;
import com.example.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingEventProducer bookingEventProducer;
    private final RestTemplate restTemplate;

    @Value("${service.schedule.base-url}")
    private String scheduleBaseUrl;

    @Value("${service.seat.base-url}")
    private String seatBaseUrl;

    public BookingService(BookingRepository bookingRepository,
                          BookingEventProducer bookingEventProducer,
                          RestTemplate restTemplate) {
        this.bookingRepository = bookingRepository;
        this.bookingEventProducer = bookingEventProducer;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request) {
        ScheduleResponse schedule = restTemplate.getForObject(
                scheduleBaseUrl + "/api/schedules/" + request.getScheduleId(), ScheduleResponse.class);

        if (schedule == null) {
            throw new IllegalArgumentException("Schedule not found");
        }

        if (!request.getMovieId().equals(schedule.getMovieId())) {
            throw new IllegalArgumentException("movieId does not match the selected schedule");
        }

        String bookingId = UUID.randomUUID().toString();
        LockSeatRequest lockSeatRequest = new LockSeatRequest();
        lockSeatRequest.setScheduleId(request.getScheduleId());
        lockSeatRequest.setBookingId(bookingId);
        lockSeatRequest.setSeats(request.getSeats());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LockSeatRequest> httpEntity = new HttpEntity<>(lockSeatRequest, headers);

        ResponseEntity<String> lockResponse = restTemplate.postForEntity(
                seatBaseUrl + "/api/seats/lock", httpEntity, String.class);

        if (!lockResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException("Failed to lock seats");
        }

        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setCustomerName(request.getCustomerName());
        booking.setEmail(request.getEmail());
        booking.setMovieId(request.getMovieId());
        booking.setScheduleId(request.getScheduleId());
        booking.setSeatsCsv(String.join(",", request.getSeats()));
        booking.setPaymentMethod(request.getPaymentMethod());
        booking.setTotalPrice(schedule.getPrice().multiply(BigDecimal.valueOf(request.getSeats().size())));
        booking.setStatus(BookingStatus.PENDING_PAYMENT);
        booking.setCreatedAt(LocalDateTime.now());

        bookingRepository.save(booking);

        BookingCreatedEvent event = new BookingCreatedEvent();
        event.setBookingId(bookingId);
        event.setCustomerName(request.getCustomerName());
        event.setEmail(request.getEmail());
        event.setMovieId(request.getMovieId());
        event.setScheduleId(request.getScheduleId());
        event.setSeats(request.getSeats());
        event.setTotalPrice(booking.getTotalPrice());
        event.setPaymentMethod(request.getPaymentMethod());

        bookingEventProducer.sendBookingCreated(event);

        return new BookingResponse(booking.getId(), booking.getStatus().name(), booking.getTotalPrice());
    }

    public Optional<Booking> findById(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Transactional
    public void updateFromPaymentResult(PaymentResultEvent event) {
        Booking booking = bookingRepository.findById(event.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found: " + event.getBookingId()));

        if ("SUCCESS".equalsIgnoreCase(event.getPaymentStatus())) {
            booking.setStatus(BookingStatus.PAID);
        } else {
            booking.setStatus(BookingStatus.PAYMENT_FAILED);
        }
        bookingRepository.save(booking);
    }
}
