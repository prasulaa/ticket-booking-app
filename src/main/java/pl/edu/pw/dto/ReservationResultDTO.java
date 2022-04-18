package pl.edu.pw.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationResultDTO {

    private Double cost;
    private LocalDate expirationDate;
    private LocalTime expirationTime;

    public ReservationResultDTO() {
    }

    public ReservationResultDTO(Double cost, LocalDate expirationDate, LocalTime expirationTime) {
        this.cost = cost;
        this.expirationDate = expirationDate;
        this.expirationTime = expirationTime;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
