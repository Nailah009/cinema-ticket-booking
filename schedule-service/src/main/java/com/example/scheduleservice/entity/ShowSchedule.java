package com.example.scheduleservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "show_schedules")
public class ShowSchedule {

    @Id
    private String id;
    private String movieId;
    private String studioName;
    private LocalDateTime showTime;
    private BigDecimal price;

    public ShowSchedule() {
    }

    public ShowSchedule(String id, String movieId, String studioName, LocalDateTime showTime, BigDecimal price) {
        this.id = id;
        this.movieId = movieId;
        this.studioName = studioName;
        this.showTime = showTime;
        this.price = price;
    }

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
