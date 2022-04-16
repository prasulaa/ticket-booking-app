package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
