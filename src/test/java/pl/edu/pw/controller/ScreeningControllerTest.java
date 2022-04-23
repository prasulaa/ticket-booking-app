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

import pl.edu.pw.dto.ScreeningDTO;
import pl.edu.pw.service.ScreeningService;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ScreeningControllerTest {

    @Mock
    private ScreeningService screeningService;
    @InjectMocks
    private ScreeningController screeningController;
    @Autowired
    private MockMvc mockMvc;

    private final String URI = "/screenings";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(screeningController).build();
    }

    @Test
    public void shouldReturnScreeningWhenIdIsPresentInDatabase() throws Exception {
        ScreeningDTO result = new ScreeningDTO(1L, null, null, null, null, null);
        when(screeningService.getScreening(any())).thenReturn(result);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/1")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(200, status);
        assertEquals(toJson(result), content);
    }

    @Test
    public void shouldNotFoundWhenIdIsNotPresentInDatabase() throws Exception {
        when(screeningService.getScreening(any())).thenThrow(new EntityNotFoundException());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/1")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(404, status);
    }

    @Test
    public void shouldReturnBadRequestWhenIdIsNotNumber() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/null")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    private String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}
