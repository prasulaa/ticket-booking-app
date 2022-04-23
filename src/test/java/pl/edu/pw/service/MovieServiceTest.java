package pl.edu.pw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pw.dto.MovieRepertoireDTO;
import pl.edu.pw.dto.ScreeningTimeDTO;
import pl.edu.pw.repository.MovieRepository;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    public void shouldReturnSortedListWhenMoviesAreInDatabase() {
        List<Object[]> repoResult = new ArrayList<>();
        Object[] screening1 = new Object[]{new BigInteger("1"), "Z-title", new BigInteger("1"), new Date(2022, 5, 10), new Time(15, 30, 0)};
        Object[] screening2 = new Object[]{new BigInteger("1"), "Z-title", new BigInteger("2"), new Date(2022, 5, 10), new Time(10, 30, 0)};
        Object[] screening3 = new Object[]{new BigInteger("2"), "A-title", new BigInteger("3"), new Date(2022, 5, 10), new Time(15, 30, 0)};
        repoResult.add(screening1);
        repoResult.add(screening2);
        repoResult.add(screening3);
        when(movieRepository.findByTimeInterval(any(), any(), any())).thenReturn(repoResult);

        List<MovieRepertoireDTO> movies = movieService.getMovies(null, null, null);

        MovieRepertoireDTO movie1 = movies.get(0);
        MovieRepertoireDTO movie2 = movies.get(1);
        List<ScreeningTimeDTO> screeningTimes1 = movie1.getScreeningTimes();
        List<ScreeningTimeDTO> screeningTimes2 = movie2.getScreeningTimes();

        assertEquals(movie1.getId(), ((BigInteger) screening3[0]).longValue());
        assertEquals(screeningTimes1.get(0).getId(), ((BigInteger) screening3[2]).longValue());
        assertEquals(movie2.getId(), ((BigInteger) screening1[0]).longValue());
        assertEquals(movie2.getId(), ((BigInteger) screening2[0]).longValue());
        assertEquals(screeningTimes2.get(0).getId(), ((BigInteger) screening2[2]).longValue());
        assertEquals(screeningTimes2.get(1).getId(), ((BigInteger) screening1[2]).longValue());
    }

    @Test
    public void shouldReturnEmptyListWhenMoviesAreNotInDatabase() {
        List<Object[]> repoResult = new ArrayList<>();
        when(movieRepository.findByTimeInterval(any(), any(), any())).thenReturn(repoResult);

        List<MovieRepertoireDTO> movies = movieService.getMovies(null, null, null);

        assertEquals(0, movies.size());
    }

}
