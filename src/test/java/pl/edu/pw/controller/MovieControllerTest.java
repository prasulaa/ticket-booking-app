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
import pl.edu.pw.dto.MovieRepertoireDTO;
import pl.edu.pw.service.MovieService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @Mock
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;
    @Autowired
    private MockMvc mockMvc;

    private final String URI = "/movies";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void shouldReturnMovieRepertoireListWhenInputIsCorrect() throws Exception{
        String uri = URI + "?date=2022-05-20&fromTime=10:00&toTime=20:00";
        List<MovieRepertoireDTO> movies = Collections.singletonList(new MovieRepertoireDTO(1L, "title", null));
        when(movieService.getMovies(any(), any(), any())).thenReturn(movies);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(200, status);
        assertEquals(toJson(movies), content);
    }

    @Test
    public void shouldReturnEmptyListWhenMovieRepertoireIsEmpty() throws Exception{
        String uri = URI + "?date=2022-05-20&fromTime=10:00&toTime=20:00";
        List<MovieRepertoireDTO> emptyList = new ArrayList<>();
        when(movieService.getMovies(any(), any(), any())).thenReturn(emptyList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(200, status);
        assertEquals(toJson(emptyList), content);
    }

    @Test
    public void shouldReturnBadRequestWhenDateFormatIsIncorrect() throws Exception{
        String uri = URI + "?date=wrongformat&fromTime=10:00&toTime=20:00";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    @Test
    public void shouldReturnBadRequestWhenTimeFormatIsIncorrect() throws Exception{
        String uri = URI + "?date=2022-05-20&fromTime=10:00&toTime=wrongformat";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    @Test
    public void shouldReturnBadRequestWhenDateIsNotPresent() throws Exception{
        String uri = URI + "?fromTime=10:00&toTime=16:00";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    @Test
    public void shouldReturnBadRequestWhenTimeIsNotPresent() throws Exception{
        String uri = URI + "?date=2022-05-20&toTime=16:00";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    @Test
    public void shouldReturnInternalServerExceptionWhenServiceThrowsUnexpectedException() throws Exception{
        when(movieService.getMovies(any(), any(), any())).thenThrow(new IllegalStateException("Unexpected"));
        String uri = URI + "?date=2022-05-20&fromTime=12:00&toTime=16:00";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(500, status);
    }

    private String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}
