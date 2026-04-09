package com.example.seatservice.repository;

import com.example.seatservice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScheduleIdOrderBySeatNumberAsc(String scheduleId);
    Optional<Seat> findByScheduleIdAndSeatNumber(String scheduleId, String seatNumber);
    List<Seat> findByBookingId(String bookingId);
}
