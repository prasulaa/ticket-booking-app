package pl.edu.pw.dto;

import java.util.List;

public class ReservationRequestDTO {

    private Long screeningId;
    private String firstName;
    private String lastName;
    private List<ReservationSeatDTO> seats;

    public ReservationRequestDTO() {
    }

    public ReservationRequestDTO(Long screeningId, String firstName, String lastName, List<ReservationSeatDTO> seats) {
        this.screeningId = screeningId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.seats = seats;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ReservationSeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<ReservationSeatDTO> seats) {
        this.seats = seats;
    }
}
