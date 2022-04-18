package pl.edu.pw.dto;

public class ReservationSeatDTO {

    private Integer row;
    private Integer seat;
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
