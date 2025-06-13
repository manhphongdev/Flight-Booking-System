package dto;

/**
 *
 * @author manhphong
 */
public class FlightSearchFilter {

    String departureCity;
    String arrivalCity;
    String departureAirportName;
    String arrivalAirportName;
    String departuretime;
    String arrivalTime;
    String airlineName;
    String ticketType;
    Double minPrice;
    Double maxPrice;
    String sortBy;
    String priceOrderType;
    String timeOrdertype;

    public FlightSearchFilter() {
    }

    public FlightSearchFilter(String departureCity, String arrivalCity, String departureAirportName, String arrivalAirportName, String departuretime, String arrivalTime, String airlineName, String ticketType, Double minPrice, Double maxPrice, String sortBy, String priceOrderType, String timeOrdertype) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.departuretime = departuretime;
        this.arrivalTime = arrivalTime;
        this.airlineName = airlineName;
        this.ticketType = ticketType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sortBy = sortBy;
        this.priceOrderType = priceOrderType;
        this.timeOrdertype = timeOrdertype;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getPriceOrderType() {
        return priceOrderType;
    }

    public void setPriceOrderType(String priceOrderType) {
        this.priceOrderType = priceOrderType;
    }

    public String getTimeOrdertype() {
        return timeOrdertype;
    }

    public void setTimeOrdertype(String timeOrdertype) {
        this.timeOrdertype = timeOrdertype;
    }

   
    
}
