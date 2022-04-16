package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT m.id, m.title, s.id as sid, s.date, s.time\n" +
            "FROM MOVIE m\n" +
            "JOIN SCREENING s ON\n" +
            "s.movie_id=m.id AND \n" +
            "s.date=?1 AND \n" +
            "s.time>=?2 AND\n" +
            "s.time<=?3", nativeQuery = true)
    List<Object[]> findByTimeInterval(LocalDate date, LocalTime fromTime, LocalTime toTime);

}
