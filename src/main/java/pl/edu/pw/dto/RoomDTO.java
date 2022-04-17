package pl.edu.pw.dto;

public class RoomDTO {

    private Long id;
    private Integer numberOfRows;
    private Integer numberOfSeatsInRow;

    public RoomDTO() {
    }

    public RoomDTO(Long id, Integer numberOfRows, Integer numberOfSeatsInRow) {
        this.id = id;
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsInRow = numberOfSeatsInRow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public Integer getNumberOfSeatsInRow() {
        return numberOfSeatsInRow;
    }

    public void setNumberOfSeatsInRow(Integer numberOfSeatsInRow) {
        this.numberOfSeatsInRow = numberOfSeatsInRow;
    }
}
