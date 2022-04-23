package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByScreening_Id(Long screeningId);

}
