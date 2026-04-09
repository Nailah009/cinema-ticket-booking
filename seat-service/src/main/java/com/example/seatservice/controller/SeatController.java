package com.example.seatservice.controller;

import com.example.seatservice.dto.LockSeatRequest;
import com.example.seatservice.dto.SeatLockResponse;
import com.example.seatservice.entity.Seat;
import com.example.seatservice.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<Seat> getSeats(@PathVariable String scheduleId) {
        return seatService.getSeatsBySchedule(scheduleId);
    }

    @PostMapping("/lock")
    public SeatLockResponse lockSeats(@Valid @RequestBody LockSeatRequest request) {
        return seatService.lockSeats(request);
    }
}
