package pl.edu.pw.service;

import pl.edu.pw.dto.MovieRepertoireDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MovieService {

    List<MovieRepertoireDTO> getMovies(LocalDate date, LocalTime fromTime, LocalTime toTime);

}
