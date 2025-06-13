
package service.interfaces;

import dto.response.FlightResponse;
import dto.request.FlightRequest;
import dto.request.FlightSearchRequest;
import dto.response.FlightSearchResponse;
import java.util.List;
import model.Flight;

/**
 *
 * @author manhphong
 */
public interface IFlightService {
     public boolean addFlight(FlightRequest flight);
     Flight getFlightById(Long id);
     List<Flight> getAll();
     void deleteById(Long id);
     void updateById(Long id,FlightRequest flight);
     
     List<FlightResponse> getAllFlight();
     
     public List<FlightSearchResponse> searchFlightForBooking(FlightSearchRequest req);

}
