package pl.edu.pw.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScreeningTimeDTO {

    private Long id;
    private LocalDate date;
    private LocalTime time;

    public ScreeningTimeDTO() {
    }

    public ScreeningTimeDTO(Long id, LocalDate date, LocalTime time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
