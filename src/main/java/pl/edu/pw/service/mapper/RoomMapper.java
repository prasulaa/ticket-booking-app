package pl.edu.pw.service.mapper;

import pl.edu.pw.domain.Room;
import pl.edu.pw.dto.RoomDTO;

public class RoomMapper {

    public static RoomDTO map(Room room) {
        if (room == null) {
            return null;
        } else {
            return new RoomDTO(
                    room.getId(),
                    room.getNumberOfRows(),
                    room.getNumberOfSeatsInRow());
        }
    }

}
