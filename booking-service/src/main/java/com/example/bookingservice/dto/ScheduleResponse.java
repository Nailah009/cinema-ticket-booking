package com.example.bookingservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ScheduleResponse {
    private String id;
    private String movieId;
    private String studioName;
    private LocalDateTime showTime;
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getStudioName() {
        return studioName;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
