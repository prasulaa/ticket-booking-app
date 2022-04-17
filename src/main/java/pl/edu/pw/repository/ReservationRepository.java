package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
