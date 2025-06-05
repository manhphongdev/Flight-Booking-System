package service.interfaces;

import java.util.List;
import model.Airline;

public interface IAirlineService {
    /**
     * save an airline object
     *
     * @param airline
     * @return true if successful
     */
    boolean save(Airline airline);

    /**
     * get airline object by airline code
     * @param code
     * @return an airline object
     */
    Airline getByCode(String code);

    /**
     * get all airline from database
     * @return a list of airline
     */
    List<Airline> getAll();

    /**
     * delete an airline entity by code
     * @param code
     * @return true if delete successful
     */
    boolean deleteByCode(String code);
} 