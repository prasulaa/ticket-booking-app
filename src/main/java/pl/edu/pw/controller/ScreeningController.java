package pl.edu.pw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.dto.ScreeningDTO;
import pl.edu.pw.service.ScreeningService;

import javax.persistence.EntityNotFoundException;

@RestController
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping("/screenings/{id}")
    public ResponseEntity<ScreeningDTO> getScreening(@PathVariable("id") Long id) {
        try {
            ScreeningDTO screening = screeningService.getScreening(id);
            return ResponseEntity.ok(screening);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
