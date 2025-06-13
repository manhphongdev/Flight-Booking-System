package dao.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import model.FlightSegment;

public interface IFlightSegmentDAO extends IBaseDAO<FlightSegment> {
    List<FlightSegment> findByFlightId(Long flightId);
    List<FlightSegment> findByAirport(String airportCode);
    List<FlightSegment> findByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    
    public FlightSegment findByDepartureAirportAndArrvalAirport(String departure, String arrival);
} 