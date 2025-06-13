package dao.interfaces;

import java.util.List;
import java.util.Optional;
import model.Airline;

/**
 * 
 * @author Administrator
 * @version 1.0
 */
public interface IAirlineDAO {

    boolean insert(Airline airline);
    List<Airline> findAll();
    Optional<Airline> findByCode(String code);
    boolean deleteByCode(String code);

    /**
     * find Airline by name
     * @param airlineName
     * @return list find by name
     */
    List<Airline> findByName(String airlineName);
    
    String getAirlineNameByAirlineId(Long id);
    
    Long getIdByAirlineName(String airlineName);
}
