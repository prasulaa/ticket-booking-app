package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
