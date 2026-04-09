package com.example.scheduleservice.repository;

import com.example.scheduleservice.entity.ShowSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowScheduleRepository extends JpaRepository<ShowSchedule, String> {
    List<ShowSchedule> findByMovieId(String movieId);
}
