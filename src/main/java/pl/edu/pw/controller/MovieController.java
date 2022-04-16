package pl.edu.pw.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.dto.MovieDTO;
import pl.edu.pw.service.MovieService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getMovies(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime fromTime,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime toTime) {
        try {
            return ResponseEntity.ok(movieService.getMovies(date, fromTime, toTime));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
