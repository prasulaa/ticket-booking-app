package pl.edu.pw.domain;

import javax.persistence.*;

@Entity
@Table
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfRows;
    private Integer numberOfSeatsInRow;

    public Room() {
    }

    public Room(Integer numberOfRows, Integer numberOfSeatsInRow) {
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsInRow = numberOfSeatsInRow;
    }

    public Room(Long id, Integer numberOfRows, Integer numberOfSeatsInRow) {
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
