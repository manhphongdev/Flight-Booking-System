package dao;

import java.util.List;
import model.Flight;

public interface IFlightDAO extends IBaseDAO<Flight> {
    List<Flight> findByAirlineId(Long airlineId);
    List<Flight> findByPriceRange(double minPrice, double maxPrice);
    boolean updatePrices(Long flightId, double economyPrice, double businessPrice);
} 