package pl.edu.pw.service;

import pl.edu.pw.dto.ReservationRequestDTO;
import pl.edu.pw.dto.ReservationResultDTO;

public interface ReservationService {

    ReservationResultDTO addReservation(ReservationRequestDTO reservation);

}
