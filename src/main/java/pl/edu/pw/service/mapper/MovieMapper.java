package pl.edu.pw.service.mapper;

import pl.edu.pw.dto.MovieDTO;
import pl.edu.pw.dto.ScreeningTimeDTO;

import java.math.BigInteger;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class MovieMapper {

    public static List<MovieDTO> map(List<Object[]> moviesDB) {
        Map<Long, MovieDTO> moviesMap = mapToMap(moviesDB);

        List<MovieDTO> moviesList = new ArrayList<>();
        for(MovieDTO m: moviesMap.values()) {
            m.getScreeningTimes().sort(Comparator.comparing(ScreeningTimeDTO::getDate).thenComparing(ScreeningTimeDTO::getTime));
            moviesList.add(m);
        }
        moviesList.sort(Comparator.comparing(MovieDTO::getTitle));
        return moviesList;
    }

    private static Map<Long, MovieDTO> mapToMap(List<Object[]> moviesDB) {
        Map<Long, MovieDTO> movies = new HashMap<>();

        for(Object[] o: moviesDB) {
            Long movieId = ((BigInteger)o[0]).longValue();
            if (movies.containsKey(movieId)) {
                MovieDTO movie = movies.get(movieId);
                movie.getScreeningTimes().add(mapToScreeningTime(o));
            } else {
                List<ScreeningTimeDTO> screeningTimes = new ArrayList<>();
                screeningTimes.add(mapToScreeningTime(o));
                MovieDTO movie = new MovieDTO(movieId, (String)o[1], screeningTimes);
                movies.put(movie.getId(), movie);
            }
        }

        return movies;
    }

    private static ScreeningTimeDTO mapToScreeningTime(Object[] o) {
        return new ScreeningTimeDTO(
                ((BigInteger)o[2]).longValue(),
                ((Date)o[3]).toLocalDate(),
                ((Time)o[4]).toLocalTime());
    }

}
