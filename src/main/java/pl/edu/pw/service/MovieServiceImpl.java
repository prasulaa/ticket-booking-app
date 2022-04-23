package pl.edu.pw.service;

import org.springframework.stereotype.Service;
import pl.edu.pw.dto.MovieRepertoireDTO;
import pl.edu.pw.repository.MovieRepository;
import pl.edu.pw.service.mapper.MovieMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieRepertoireDTO> getMovies(LocalDate date, LocalTime fromTime, LocalTime toTime) {
        List<Object[]> moviesDB = movieRepository.findByTimeInterval(date, fromTime, toTime);
        return MovieMapper.map(moviesDB);
    }


}
