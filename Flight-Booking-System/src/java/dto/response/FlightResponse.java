package dto.response;

/**
 *
 * @author manhphong
 */
public class FlightResponse {

    private Long flightId;
    private String airlineName;
    private Double economyPrice;
    private Double businessPrice;

    public FlightResponse() {
    }

    public FlightResponse(Long flightId, String AirlineName, Double economyPrice, Double businessPrice) {
        this.flightId = flightId;
        this.airlineName = AirlineName;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String AirlineName) {
        this.airlineName = AirlineName;
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

    @Override
    public String toString() {
        return "FlightResponse{" + "flightId=" + flightId + ", airlineName=" + airlineName + ", economyPrice=" + economyPrice + ", businessPrice=" + businessPrice + '}';
    }
}
