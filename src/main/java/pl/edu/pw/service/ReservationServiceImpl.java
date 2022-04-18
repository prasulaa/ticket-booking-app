package pl.edu.pw.service;

import org.springframework.stereotype.Service;
import pl.edu.pw.domain.Reservation;
import pl.edu.pw.domain.Room;
import pl.edu.pw.domain.Screening;
import pl.edu.pw.domain.TicketType;
import pl.edu.pw.dto.ReservationRequestDTO;
import pl.edu.pw.dto.ReservationResultDTO;
import pl.edu.pw.dto.ReservationSeatDTO;
import pl.edu.pw.repository.ReservationRepository;
import pl.edu.pw.repository.ScreeningRepository;
import pl.edu.pw.service.mapper.ReservationMapper;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ScreeningRepository screeningRepository) {
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
    }

    @Override
    public ReservationResultDTO addReservation(ReservationRequestDTO reservationReq) {
        List<Reservation> reservations = reservationRepository.findAllByScreening_Id(reservationReq.getScreeningId());
        Optional<Screening> screeningOpt = screeningRepository.findById(reservationReq.getScreeningId());

        if (screeningOpt.isPresent()) {
            Screening screening = screeningOpt.get();
            validateSeats(reservationReq, reservations, screening.getRoom());

            List<Reservation> mappedReservations = ReservationMapper.map(reservationReq, screening);
            screening.getReservations().addAll(mappedReservations);
            screeningRepository.save(screening);

            return reservationResult(mappedReservations, screening);
        } else {
            throw new IllegalArgumentException("Screening does not exist");
        }
    }

    private ReservationResultDTO reservationResult(List<Reservation> reservations, Screening screening) {
        return new ReservationResultDTO(
                calculateReservationCost(reservations),
                screening.getDate(),
                screening.getTime()
        );
    }

    private double calculateReservationCost(List<Reservation> reservations) {
        double cost = 0.0;

        for (Reservation r: reservations) {
            cost += TicketType.ticketCost(r.getTicketType());
        }

        return cost;
    }

    private void validateSeats(ReservationRequestDTO reservationReq, List<Reservation> reservations, Room room) {
        validateIfSeatsAreFree(reservationReq, reservations);

        if (isSinglePlaceLeftOverInRow(reservationReq.getSeats(), reservations, room)) {
            throw new IllegalArgumentException("There cannot be a single place left over in a row " +
                    "between two already reserved places");
        }
    }

    private void validateIfSeatsAreFree(ReservationRequestDTO reservationReq, List<Reservation> reservations) {
        for (ReservationSeatDTO seat : reservationReq.getSeats()) {
            if (!isSeatFree(seat, reservations)) {
                throw new IllegalArgumentException("Seat [" + seat.getRow() + ", " + seat.getSeat() + "] is not free");
            }
        }
    }

    private boolean isSinglePlaceLeftOverInRow(List<ReservationSeatDTO> seats, List<Reservation> reservations, Room room) {
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

    private boolean isSinglePlaceLeftOnLeft(int row, int seat, boolean[][] reservedSeats) {
        try {
            return reservedSeats[row][seat - 2] && !reservedSeats[row][seat - 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    private boolean isSinglePlaceLeftOnRight(int row, int seat, boolean[][] reservedSeats) {
        try {
            return reservedSeats[row][seat + 2] && !reservedSeats[row][seat + 1];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    private boolean[][] getReservedSeatsAsArray(List<ReservationSeatDTO> seats, List<Reservation> reservations, Room room) {
        boolean[][] reservedSeats = new boolean[room.getNumberOfRows()][room.getNumberOfSeatsInRow()];

        for (Reservation r : reservations) {
            reservedSeats[r.getSeatRow()][r.getSeatNumber()] = true;
        }

        for (ReservationSeatDTO s : seats) {
            reservedSeats[s.getRow()][s.getSeat()] = true;
        }

        return reservedSeats;
    }

    private boolean isSeatFree(ReservationSeatDTO seat, List<Reservation> reservations) {
        for (Reservation r : reservations) {
            if (seat.getRow().equals(r.getSeatRow()) && seat.getSeat().equals(r.getSeatNumber())) {
                return false;
            }
        }
        return true;
    }
}
