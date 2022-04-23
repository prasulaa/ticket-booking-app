package pl.edu.pw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pw.domain.Reservation;
import pl.edu.pw.domain.Room;
import pl.edu.pw.domain.Screening;
import pl.edu.pw.dto.*;
import pl.edu.pw.repository.ScreeningRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;
    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    public void shouldReturnCorrectResultWhenReservationRequestIsCorrect() {
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(3, 3, "adult"));
        ReservationRequestDTO reservationRequest = new ReservationRequestDTO(1L, "First", "Last", seats);

        List<Reservation> reservationsMock = new ArrayList<>();
        Screening screeningMock = new Screening(1L, LocalDate.parse("2025-10-05"), LocalTime.parse("15:00"), null, new Room(1L, 5, 5), reservationsMock);
        Optional<Screening> screeningOptionalMock = Optional.of(screeningMock);

        when(screeningRepository.findById(any())).thenReturn(screeningOptionalMock);

        ReservationResultDTO result = reservationService.addReservation(reservationRequest);

        assertEquals(25, result.getCost());
        assertEquals(LocalDate.parse("2025-10-05"), result.getExpirationDate());
        assertEquals(LocalTime.parse("15:00"), result.getExpirationTime());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenScreeningDoesNotExist() {
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(3, 3, "adult"));
        ReservationRequestDTO reservationRequest = new ReservationRequestDTO(1L, "First", "Last", seats);

        when(screeningRepository.findById(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.addReservation(reservationRequest));

        assertEquals("Screening does not exist", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSeatIsNotInTheRoom() {
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(10, 10, "adult"));
        ReservationRequestDTO reservationRequest = new ReservationRequestDTO(1L, "First", "Last", seats);

        List<Reservation> reservationsMock = new ArrayList<>();
        Screening screeningMock = new Screening(1L, LocalDate.parse("2025-10-05"), LocalTime.parse("15:00"), null, new Room(1L, 5, 5), reservationsMock);
        Optional<Screening> screeningOptionalMock = Optional.of(screeningMock);

        when(screeningRepository.findById(any())).thenReturn(screeningOptionalMock);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.addReservation(reservationRequest));

        assertEquals("No such seat in the screening room", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSeatIsNotFree() {
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(3, 3, "adult"));
        ReservationRequestDTO reservationRequest = new ReservationRequestDTO(1L, "First", "Last", seats);

        List<Reservation> reservationsMock = Collections.singletonList(new Reservation(1L, 3, 3, "Abc", "Bca", null, null));
        Screening screeningMock = new Screening(1L, LocalDate.parse("2025-10-05"), LocalTime.parse("15:00"), null, new Room(1L, 5, 5), reservationsMock);
        Optional<Screening> screeningOptionalMock = Optional.of(screeningMock);

        when(screeningRepository.findById(any())).thenReturn(screeningOptionalMock);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.addReservation(reservationRequest));

        assertEquals("Seat [3, 3] is not free", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTicketTypeIsIncorrect() {
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(3, 3, "abcabcabc"));
        ReservationRequestDTO reservationRequest = new ReservationRequestDTO(1L, "First", "Last", seats);

        List<Reservation> reservationsMock = new ArrayList<>();
        Screening screeningMock = new Screening(1L, LocalDate.parse("2025-10-05"), LocalTime.parse("15:00"), null, new Room(1L, 5, 5), reservationsMock);
        Optional<Screening> screeningOptionalMock = Optional.of(screeningMock);

        when(screeningRepository.findById(any())).thenReturn(screeningOptionalMock);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.addReservation(reservationRequest));

        assertEquals("Wrong ticket type abcabcabc", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameHasWrongFormat() {
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(3, 3, "adult"));
        ReservationRequestDTO reservationRequest = new ReservationRequestDTO(1L, "first", "Last", seats);

        List<Reservation> reservationsMock = new ArrayList<>();
        Screening screeningMock = new Screening(1L, LocalDate.parse("2025-10-05"), LocalTime.parse("15:00"), null, new Room(1L, 5, 5), reservationsMock);
        Optional<Screening> screeningOptionalMock = Optional.of(screeningMock);

        when(screeningRepository.findById(any())).thenReturn(screeningOptionalMock);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.addReservation(reservationRequest));

        assertEquals("Fist character of name must be capital letter", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenScreeningHasTakenPlace() {
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(3, 3, "adult"));
        ReservationRequestDTO reservationRequest = new ReservationRequestDTO(1L, "First", "Last", seats);

        List<Reservation> reservationsMock = new ArrayList<>();
        Screening screeningMock = new Screening(1L, LocalDate.parse("2000-10-05"), LocalTime.parse("15:00"), null, new Room(1L, 5, 5), reservationsMock);
        Optional<Screening> screeningOptionalMock = Optional.of(screeningMock);

        when(screeningRepository.findById(any())).thenReturn(screeningOptionalMock);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.addReservation(reservationRequest));

        assertEquals("The screening has already taken place", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenReservationLeavesSingleSeat() {
        List<ReservationSeatDTO> seats = new ArrayList<>();
        seats.add(new ReservationSeatDTO(3, 2, "adult"));
        seats.add(new ReservationSeatDTO(3, 4, "adult"));
        ReservationRequestDTO reservationRequest = new ReservationRequestDTO(1L, "First", "Last", seats);

        List<Reservation> reservationsMock = new ArrayList<>();
        Screening screeningMock = new Screening(1L, LocalDate.parse("2025-10-05"), LocalTime.parse("15:00"), null, new Room(1L, 5, 5), reservationsMock);
        Optional<Screening> screeningOptionalMock = Optional.of(screeningMock);

        when(screeningRepository.findById(any())).thenReturn(screeningOptionalMock);

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.addReservation(reservationRequest));

        assertEquals("There cannot be a single place left over in a row between two already reserved places", exception.getMessage());
    }

}
