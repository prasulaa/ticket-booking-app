package pl.edu.pw.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScreeningDTO {

    private Long id;
    private LocalDate date;
    private LocalTime time;
    private MovieDTO movie;
    private RoomDTO room;
    private List<SeatDTO> availableSeats;

    public ScreeningDTO() {
    }

    public ScreeningDTO(Long id, LocalDate date, LocalTime time, MovieDTO movie, RoomDTO room, List<SeatDTO> availableSeats) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.movie = movie;
        this.room = room;
        this.availableSeats = availableSeats;
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

    public MovieDTO getMovie() {
        return movie;
    }

    public void setMovie(MovieDTO movie) {
        this.movie = movie;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public List<SeatDTO> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<SeatDTO> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
