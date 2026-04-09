package com.example.seatservice.event;

import java.math.BigDecimal;
import java.util.List;

public class PaymentResultEvent {

    private String bookingId;
    private String scheduleId;
    private List<String> seats;
    private BigDecimal amount;
    private String paymentMethod;
    private String email;
    private String customerName;
    private String paymentStatus;
    private String reason;

    public PaymentResultEvent() {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getReason() {
        return reason;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
