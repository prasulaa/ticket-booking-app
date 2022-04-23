package pl.edu.pw.service.mapper;

import pl.edu.pw.domain.Reservation;
import pl.edu.pw.domain.Room;
import pl.edu.pw.dto.SeatDTO;

import java.util.ArrayList;
import java.util.List;

public class SeatsMapper {

    public static List<SeatDTO> mapAvailableSeats(Room room, List<Reservation> reservations) {
        if (room == null || reservations == null) {
            return null;
        } else {
            return mapAvailableSeats(
                    reservations,
                    room.getNumberOfRows(),
                    room.getNumberOfSeatsInRow());
        }
    }

    private static List<SeatDTO> mapAvailableSeats(List<Reservation> reservations, int numberOfRows, int numberOfSeats) {
        List<SeatDTO> availableSeats = new ArrayList<>();

        for (int row = 0; row < numberOfRows; row++) {
            for (int seat = 0; seat < numberOfSeats; seat++) {
                if (isAvailable(reservations, row, seat)) {
                    availableSeats.add(new SeatDTO(row, seat));
                }
            }
        }

        return availableSeats;
    }

    private static boolean isAvailable(List<Reservation> reservations, int row, int seat) {
        for (Reservation r : reservations) {
            if (r.getSeatRow() == row && r.getSeatNumber() == seat) {
                return false;
            }
        }
        return true;
    }

}
