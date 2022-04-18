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
import pl.edu.pw.service.validation.ReservationRequestValidator;

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
            ReservationRequestValidator.validate(reservationReq, reservations, screening.getRoom());

            List<Reservation> mappedReservations = ReservationMapper.map(reservationReq, screening);
            saveReservation(mappedReservations, screening);

            return reservationResult(mappedReservations, screening);
        } else {
            throw new IllegalArgumentException("Screening does not exist");
        }
    }

    private void saveReservation(List<Reservation> reservations, Screening screening) {
        screening.getReservations().addAll(reservations);
        screeningRepository.save(screening);
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

}
