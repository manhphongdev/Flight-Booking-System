package dto.response;


/**
 *
 * @author manhphong
 */
public class FlightSearchResponse {

    private Long flightId;
    private String price;
    private String airlineName;
    private String airlineCode;
    private String departureAirportCode;
    private String departureAirportName;
    private String departureAirportCity;
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCity;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;

    public FlightSearchResponse() {
    }

    public FlightSearchResponse(Long flightId, String price, String airlineName, String airlineCode, String departureAirportCode, String departureAirportName, String departureAirportCity, String arrivalAirportCode, String arrivalAirportName, String arrivalAirportCity, String flightNumber, String departureTime, String arrivalTime) {
        this.flightId = flightId;
        this.price = price;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.departureAirportCode = departureAirportCode;
        this.departureAirportName = departureAirportName;
        this.departureAirportCity = departureAirportCity;
        this.arrivalAirportCode = arrivalAirportCode;
        this.arrivalAirportName = arrivalAirportName;
        this.arrivalAirportCity = arrivalAirportCity;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDepartureAirportCity() {
        return departureAirportCity;
    }

    public void setDepartureAirportCity(String departureAirportCity) {
        this.departureAirportCity = departureAirportCity;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getArrivalAirportCity() {
        return arrivalAirportCity;
    }

    public void setArrivalAirportCity(String arrivalAirportCity) {
        this.arrivalAirportCity = arrivalAirportCity;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "FlightSearchResponse{" + "flightId=" + flightId + ", price=" + price + ", airlineName=" + airlineName + ", airlineCode=" + airlineCode + ", departureAirportCode=" + departureAirportCode + ", departureAirportName=" + departureAirportName + ", departureAirportCity=" + departureAirportCity + ", arrivalAirportCode=" + arrivalAirportCode + ", arrivalAirportName=" + arrivalAirportName + ", arrivalAirportCity=" + arrivalAirportCity + ", flightNumber=" + flightNumber + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + '}';
    }

    
}
