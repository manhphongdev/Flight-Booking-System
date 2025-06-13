package dto.request;

import java.time.LocalDateTime;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class FlightScheduleRequest {

    private Long scheduleId;
    private Long flightName;
    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer duration;

    public FlightScheduleRequest() {
    }

    public FlightScheduleRequest(Long scheduleId, Long flightName, String flightNumber, LocalDateTime departureTime, LocalDateTime arrivalTime, Integer duration) {
        this.scheduleId = scheduleId;
        this.flightName = flightName;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getFlightName() {
        return flightName;
    }

    public void setFlightName(Long flightName) {
        this.flightName = flightName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "FlightScheduleRequest{" + "scheduleId=" + scheduleId + ", flightName=" + flightName + ", flightNumber=" + flightNumber + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", duration=" + duration + '}';
    }

}
