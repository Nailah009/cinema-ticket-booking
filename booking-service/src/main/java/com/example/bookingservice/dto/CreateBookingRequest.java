package com.example.bookingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateBookingRequest {

    @NotBlank(message = "customerName is required")
    private String customerName;

    @Email(message = "email format is invalid")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "movieId is required")
    private String movieId;

    @NotBlank(message = "scheduleId is required")
    private String scheduleId;

    @NotEmpty(message = "seats must not be empty")
    private List<String> seats;

    @NotBlank(message = "paymentMethod is required")
    private String paymentMethod;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
