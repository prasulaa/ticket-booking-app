package pl.edu.pw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pw.dto.ReservationRequestDTO;
import pl.edu.pw.dto.ReservationResultDTO;
import pl.edu.pw.service.ReservationService;

import javax.validation.Valid;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResultDTO> addReservation(@RequestBody @Valid ReservationRequestDTO reservations) {
        try {
            return ResponseEntity.ok(reservationService.addReservation(reservations));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
