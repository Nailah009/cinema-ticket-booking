package com.example.bookingservice.event;

import java.math.BigDecimal;
import java.util.List;

public class BookingCreatedEvent {

    private String bookingId;
    private String customerName;
    private String email;
    private String movieId;
    private String scheduleId;
    private List<String> seats;
    private BigDecimal totalPrice;
    private String paymentMethod;

    public BookingCreatedEvent() {
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public List<String> getSeats() {
        return seats;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
