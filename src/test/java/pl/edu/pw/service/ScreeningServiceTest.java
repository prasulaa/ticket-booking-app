package pl.edu.pw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pw.domain.Movie;
import pl.edu.pw.domain.Reservation;
import pl.edu.pw.domain.Room;
import pl.edu.pw.domain.Screening;
import pl.edu.pw.dto.*;
import pl.edu.pw.repository.ScreeningRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;
    @InjectMocks
    private ScreeningServiceImpl screeningService;

    @Test
    public void shouldReturnScreeningWhenIdIsInDatabase() {
        List<Reservation> reservationsMock = new ArrayList<>();
        Movie movieMock = new Movie("Title1");
        Room roomMock = new Room(1L, 5, 5);
        Screening screeningMock = new Screening(1L, LocalDate.now(), LocalTime.now(), movieMock, roomMock, reservationsMock);

        when(screeningRepository.findById(any())).thenReturn(Optional.of(screeningMock));
        ScreeningDTO result = screeningService.getScreening(1L);

        assertEquals(screeningMock.getId(), result.getId());
        assertEquals(screeningMock.getDate(), result.getDate());
        assertEquals(screeningMock.getTime(), result.getTime());
        assertEquals(screeningMock.getMovie().getTitle(), result.getMovie().getTitle());
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenIdIsNotInDatabase() {
        when(screeningRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> screeningService.getScreening(1L));
    }

}
