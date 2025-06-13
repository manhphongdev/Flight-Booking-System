package dao.interfaces;

import dto.FlightSearchDTO;
import dto.FlightSearchFilter;
import java.util.List;
import model.Flight;

public interface IFlightDAO extends IBaseDAO<Flight> {

    List<Flight> findByAirlineId(Long airlineId);

    List<Flight> findByPriceRange(double minPrice, double maxPrice);

    boolean updatePrices(Long flightId, double economyPrice, double businessPrice);

    public List<FlightSearchDTO> searchFlightOneWayAndFilterWithCondition(FlightSearchFilter filter);
    public List<FlightSearchDTO> searchFlightForBookingOneWay(String departure,
            String arrival, String departureDate);

    FlightSearchDTO searchByFlightId(Long id);
}
