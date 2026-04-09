package com.example.seatservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seats", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"scheduleId", "seatNumber"})
})
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scheduleId;
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private String bookingId;

    public Seat() {
    }

    public Seat(String scheduleId, String seatNumber, SeatStatus status, String bookingId) {
        this.scheduleId = scheduleId;
        this.seatNumber = seatNumber;
        this.status = status;
        this.bookingId = bookingId;
    }

    public Long getId() {
        return id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
