package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pw.domain.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
