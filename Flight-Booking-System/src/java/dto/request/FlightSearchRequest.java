
package dto.request;


/**
 *
 * @author manhphong
 */
public class FlightSearchRequest {
    String departureFrom;
    String arrivalTo;
    String dateFrom;
    String dateReturn;
    String ticketClass;
    int  adultNumber;
    int childNumber;

    public FlightSearchRequest() {
    }

    public FlightSearchRequest(String departureFrom, String arrivalTo, String dateFrom, String dateReturn, String ticketClass) {
        this.departureFrom = departureFrom;
        this.arrivalTo = arrivalTo;
        this.dateFrom = dateFrom;
        this.dateReturn = dateReturn;
        this.ticketClass = ticketClass;
    }

    public FlightSearchRequest(String departureFrom, String arrivalTo, String dateFrom, String dateReturn, String ticketClass, int adultNumber, int childNumber) {
        this.departureFrom = departureFrom;
        this.arrivalTo = arrivalTo;
        this.dateFrom = dateFrom;
        this.dateReturn = dateReturn;
        this.ticketClass = ticketClass;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
    }
    
    

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getArrivalTo() {
        return arrivalTo;
    }

    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public int getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }

    

 
}
