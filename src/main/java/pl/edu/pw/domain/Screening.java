package pl.edu.pw.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne(cascade = CascadeType.ALL)
    private Movie movie;
    @ManyToOne
    private Room room;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Screening() {
    }

    public Screening(LocalDate date, LocalTime time, Movie movie, Room room) {
        this.date = date;
        this.time = time;
        this.movie = movie;
        this.room = room;
    }

    public Screening(Long id, LocalDate date, LocalTime time, Movie movie, Room room, List<Reservation> reservations) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.movie = movie;
        this.room = room;
        this.reservations = reservations;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
