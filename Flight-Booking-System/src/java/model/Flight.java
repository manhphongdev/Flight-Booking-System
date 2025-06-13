package model;

import java.time.LocalDateTime;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class Flight {

    private Long flightId;
    private Long airlineId;
    private Double economyPrice;
    private Double businessPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Flight() {
    }

    public Flight(Long airlineId, Double economyPrice, Double businessPrice, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.airlineId = airlineId;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Flight(Long flightId, Long airlineId, Double economyPrice, Double businessPrice, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.flightId = flightId;
        this.airlineId = airlineId;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public Double getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(Double economyPrice) {
        this.economyPrice = economyPrice;
    }

    public Double getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(Double businessPrice) {
        this.businessPrice = businessPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Flight{" + "flightId=" + flightId + ", airlineId=" + airlineId + ", economyPrice=" + economyPrice + ", businessPrice=" + businessPrice + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
   
}
