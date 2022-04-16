package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.domain.Screening;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
