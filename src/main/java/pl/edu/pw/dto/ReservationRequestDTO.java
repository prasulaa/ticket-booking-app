package pl.edu.pw.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ReservationRequestDTO {

    @NotNull(message = "Screening ID has to be present")
    private Long screeningId;
    @NotNull(message = "First Name has to be present")
    @NotBlank(message = "First Name cannot be empty")
    private String firstName;
    @NotNull(message = "Last Name has to be present")
    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;
    @NotNull(message = "Seat list has to be present")
    @NotEmpty(message = "Seat list must contain at least one seat")
    @Valid
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
