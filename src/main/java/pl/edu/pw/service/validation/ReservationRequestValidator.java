package pl.edu.pw.service.validation;

import pl.edu.pw.domain.Reservation;
import pl.edu.pw.domain.Room;
import pl.edu.pw.domain.Screening;
import pl.edu.pw.dto.ReservationRequestDTO;
import pl.edu.pw.dto.ReservationSeatDTO;

import java.util.List;

public class ReservationRequestValidator {

    public static void validate(ReservationRequestDTO reservationReq, List<Reservation> reservations, Room room) {
        validateNames(reservationReq);
        validateSeats(reservationReq, reservations, room);
    }

    private static void validateSeats(ReservationRequestDTO reservationReq, List<Reservation> reservations, Room room) {
        validateIfSeatsAreFree(reservationReq, reservations);

        if (isSinglePlaceLeftOverInRow(reservationReq.getSeats(), reservations, room)) {
            throw new IllegalArgumentException("There cannot be a single place left over in a row " +
                    "between two already reserved places");
        }
    }

    private static void validateIfSeatsAreFree(ReservationRequestDTO reservationReq, List<Reservation> reservations) {
        for (ReservationSeatDTO seat : reservationReq.getSeats()) {
            if (!isSeatFree(seat, reservations)) {
                throw new IllegalArgumentException("Seat [" + seat.getRow() + ", " + seat.getSeat() + "] is not free");
            }
        }
    }

    private static boolean isSinglePlaceLeftOverInRow(List<ReservationSeatDTO> seats, List<Reservation> reservations, Room room) {
        boolean[][] reservedSeats = getReservedSeatsAsArray(seats, reservations, room);

        for (ReservationSeatDTO seat : seats) {
            int r = seat.getRow();
            int s = seat.getSeat();

            if (isSinglePlaceLeftOnLeft(r, s, reservedSeats) || isSinglePlaceLeftOnRight(r, s, reservedSeats)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSinglePlaceLeftOnLeft(int row, int seat, boolean[][] reservedSeats) {
        try {
            return reservedSeats[row][seat - 2] && !reservedSeats[row][seat - 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    private static boolean isSinglePlaceLeftOnRight(int row, int seat, boolean[][] reservedSeats) {
        try {
            return reservedSeats[row][seat + 2] && !reservedSeats[row][seat + 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    private static boolean[][] getReservedSeatsAsArray(List<ReservationSeatDTO> seats, List<Reservation> reservations, Room room) {
        boolean[][] reservedSeats = new boolean[room.getNumberOfRows()][room.getNumberOfSeatsInRow()];

        for (Reservation r : reservations) {
            reservedSeats[r.getSeatRow()][r.getSeatNumber()] = true;
        }

        for (ReservationSeatDTO s : seats) {
            reservedSeats[s.getRow()][s.getSeat()] = true;
        }

        return reservedSeats;
    }

    private static boolean isSeatFree(ReservationSeatDTO seat, List<Reservation> reservations) {
        for (Reservation r : reservations) {
            if (seat.getRow().equals(r.getSeatRow()) && seat.getSeat().equals(r.getSeatNumber())) {
                return false;
            }
        }
        return true;
    }

    private static void validateNames(ReservationRequestDTO reservationReq) {
        validateName(reservationReq.getFirstName());
        validateLastName(reservationReq.getLastName());
    }

    private static void validateLastName(String lastName) {
        String[] lastNames = lastName.split("-");
        validateName(lastNames[0]);

        if (lastNames.length == 2) {
            validateName(lastNames[1]);
        } else if (lastNames.length > 2) {
            throw new IllegalArgumentException("Wrong last name format");
        }
    }

    private static void validateName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at lest 3 characters long");
        }
        if (!Character.isUpperCase(name.charAt(0))) {
            throw new IllegalArgumentException("Fist character of name must be capital letter");
        }
    }

}
