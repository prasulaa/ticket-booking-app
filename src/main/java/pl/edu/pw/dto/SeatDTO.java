package pl.edu.pw.dto;

public class SeatDTO {

    private Integer row;
    private Integer seat;

    public SeatDTO() {
    }

    public SeatDTO(Integer row, Integer seat) {
        this.row = row;
        this.seat = seat;
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
}
