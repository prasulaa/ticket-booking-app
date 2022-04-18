package pl.edu.pw.service.mapper;

import pl.edu.pw.domain.Reservation;
import pl.edu.pw.domain.Screening;
import pl.edu.pw.domain.TicketType;
import pl.edu.pw.dto.ReservationRequestDTO;
import pl.edu.pw.dto.ReservationSeatDTO;

import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {

    public static List<Reservation> map(ReservationRequestDTO reservationReq, Screening screening) {
        List<Reservation> mappedReservations = new ArrayList<>();

        for (ReservationSeatDTO seat: reservationReq.getSeats()) {
            Reservation reservation = map(seat, reservationReq, screening);
            mappedReservations.add(reservation);
        }

        return mappedReservations;
    }

    private static Reservation map(ReservationSeatDTO seat, ReservationRequestDTO reservationReq, Screening screening) {
        Reservation reservation = new Reservation();
        reservation.setSeatRow(seat.getRow());
        reservation.setSeatNumber(seat.getSeat());
        reservation.setFirstName(reservationReq.getFirstName());
        reservation.setLastName(reservationReq.getLastName());
        reservation.setTicketType(TicketType.parse(seat.getTicketType()));
        reservation.setScreening(screening);

        return reservation;
    }

}
