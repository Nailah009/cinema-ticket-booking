package com.example.seatservice.service;

import com.example.seatservice.dto.LockSeatRequest;
import com.example.seatservice.dto.SeatLockResponse;
import com.example.seatservice.entity.Seat;
import com.example.seatservice.entity.SeatStatus;
import com.example.seatservice.event.PaymentResultEvent;
import com.example.seatservice.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeatsBySchedule(String scheduleId) {
        return seatRepository.findByScheduleIdOrderBySeatNumberAsc(scheduleId);
    }

    @Transactional
    public SeatLockResponse lockSeats(LockSeatRequest request) {
        for (String seatNumber : request.getSeats()) {
            Seat seat = seatRepository.findByScheduleIdAndSeatNumber(request.getScheduleId(), seatNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Seat not found: " + seatNumber));

            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new IllegalStateException("Seat is not available: " + seatNumber);
            }

            seat.setStatus(SeatStatus.LOCKED);
            seat.setBookingId(request.getBookingId());
            seatRepository.save(seat);
        }

        return new SeatLockResponse(request.getBookingId(), request.getScheduleId(), request.getSeats(), "LOCKED");
    }

    @Transactional
    public void confirmSeats(PaymentResultEvent event) {
        for (String seatNumber : event.getSeats()) {
            Seat seat = seatRepository.findByScheduleIdAndSeatNumber(event.getScheduleId(), seatNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Seat not found: " + seatNumber));

            if (event.getBookingId().equals(seat.getBookingId())) {
                seat.setStatus(SeatStatus.BOOKED);
                seatRepository.save(seat);
            }
        }
    }

    @Transactional
    public void releaseSeats(PaymentResultEvent event) {
        List<Seat> seats = seatRepository.findByBookingId(event.getBookingId());
        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setBookingId(null);
            seatRepository.save(seat);
        }
    }
}
