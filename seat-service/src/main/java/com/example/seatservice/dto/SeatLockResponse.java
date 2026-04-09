package com.example.seatservice.dto;

import java.util.List;

public class SeatLockResponse {

    private String bookingId;
    private String scheduleId;
    private List<String> seats;
    private String status;

    public SeatLockResponse() {
    }

    public SeatLockResponse(String bookingId, String scheduleId, List<String> seats, String status) {
        this.bookingId = bookingId;
        this.scheduleId = scheduleId;
        this.seats = seats;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public List<String> getSeats() {
        return seats;
    }

    public String getStatus() {
        return status;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
