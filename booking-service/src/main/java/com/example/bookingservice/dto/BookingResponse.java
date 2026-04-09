package com.example.bookingservice.dto;

import java.math.BigDecimal;

public class BookingResponse {

    private String bookingId;
    private String status;
    private BigDecimal totalPrice;

    public BookingResponse() {
    }

    public BookingResponse(String bookingId, String status, BigDecimal totalPrice) {
        this.bookingId = bookingId;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
