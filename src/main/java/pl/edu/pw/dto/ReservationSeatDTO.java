package pl.edu.pw.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReservationSeatDTO {

    @NotNull(message = "Row number has to be present")
    private Integer row;
    @NotNull(message = "Seat number has to be present")
    private Integer seat;
    @NotNull(message = "Ticket type has to be present")
    @NotBlank(message = "Ticket type has to be present")
    private String ticketType;

    public ReservationSeatDTO() {
    }

    public ReservationSeatDTO(Integer row, Integer seat, String ticketType) {
        this.row = row;
        this.seat = seat;
        this.ticketType = ticketType;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
