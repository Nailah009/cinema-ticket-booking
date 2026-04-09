package com.example.seatservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class LockSeatRequest {

    @NotBlank(message = "scheduleId is required")
    private String scheduleId;

    @NotBlank(message = "bookingId is required")
    private String bookingId;

    @NotEmpty(message = "seats must not be empty")
    private List<String> seats;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }
}
