package pl.edu.pw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pw.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
