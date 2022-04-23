package pl.edu.pw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pw.dto.ReservationRequestDTO;
import pl.edu.pw.dto.ReservationResultDTO;
import pl.edu.pw.dto.ReservationSeatDTO;
import pl.edu.pw.service.ReservationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;
    @InjectMocks
    private ReservationController reservationController;
    @Autowired
    private MockMvc mockMvc;

    private final String URI = "/reservations";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    public void shouldReturnReservationResultWhenInputIsCorrect() throws Exception{
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(1, 1, "adult"));
        ReservationRequestDTO request = new ReservationRequestDTO(1L, "First", "Last", seats);
        ReservationResultDTO result = new ReservationResultDTO(50.0, null, null);
        when(reservationService.addReservation(any())).thenReturn(result);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(200, status);
        assertEquals(toJson(result), content);
    }

    @Test
    public void shouldReturnBadRequestWhenContentIsNotPresent() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    @Test
    public void shouldReturnBadRequestWhenContentIsNull() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(null))).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    // wrong reservations data * 4
    @Test
    public void shouldReturnBadRequestWhenScreeningIdIsNull() throws Exception{
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(1, 1, "adult"));
        ReservationRequestDTO request = new ReservationRequestDTO(null, "First", "Last", seats);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    @Test
    public void shouldReturnBadRequestWhenFirstNameIsEmpty() throws Exception{
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(1, 1, "adult"));
        ReservationRequestDTO request = new ReservationRequestDTO(1L, "", "Last", seats);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    @Test
    public void shouldReturnBadRequestWhenLastNameIsEmpty() throws Exception{
        List<ReservationSeatDTO> seats = Collections.singletonList(new ReservationSeatDTO(1, 1, "adult"));
        ReservationRequestDTO request = new ReservationRequestDTO(1L, "First", "", seats);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    @Test
    public void shouldReturnBadRequestWhenSeatListIsEmpty() throws Exception{
        List<ReservationSeatDTO> seats = new ArrayList<>();
        ReservationRequestDTO request = new ReservationRequestDTO(1L, "First", "Last", seats);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    private String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}
