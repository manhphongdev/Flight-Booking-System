
package dto.request;

/**
 *
 * @author manhphong
 */
public class FlightRequest {

    private String airlineName;
    private Double economyPrice;
    private Double businessPrice;

    public FlightRequest() {
    }

    public FlightRequest(String airlineName, Double economyPrice, Double businessPrice) {
        this.airlineName = airlineName;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
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
    
    
}
