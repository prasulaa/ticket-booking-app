package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
