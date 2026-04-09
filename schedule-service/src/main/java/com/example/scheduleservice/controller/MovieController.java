package com.example.scheduleservice.controller;

import com.example.scheduleservice.entity.Movie;
import com.example.scheduleservice.entity.ShowSchedule;
import com.example.scheduleservice.repository.MovieRepository;
import com.example.scheduleservice.repository.ShowScheduleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieRepository movieRepository;
    private final ShowScheduleRepository showScheduleRepository;

    public MovieController(MovieRepository movieRepository, ShowScheduleRepository showScheduleRepository) {
        this.movieRepository = movieRepository;
        this.showScheduleRepository = showScheduleRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{movieId}/schedules")
    public List<ShowSchedule> getSchedulesByMovie(@PathVariable String movieId) {
        return showScheduleRepository.findByMovieId(movieId);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ShowSchedule getSchedule(@PathVariable String scheduleId) {
        return showScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found: " + scheduleId));
    }
}
