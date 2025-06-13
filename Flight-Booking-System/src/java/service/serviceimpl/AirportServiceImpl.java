
package service.serviceimpl;

import dao.interfaces.IAirportDAO;
import dao.daoimpl.AirportDAOImpl;
import exception.EntityExistExeption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import model.Airport;
import service.interfaces.IAirportService;

/**
 *
 * @author manhphong
 */
public class AirportServiceImpl implements IAirportService {

    private final IAirportDAO airportDAO;
    private static final Logger LOG = Logger.getLogger(AirportServiceImpl.class.getName());

    public AirportServiceImpl() {
        this.airportDAO = new AirportDAOImpl();
    }

    @Override
    public boolean save(Airport airport) {
        // check airport is exist
        if (airportDAO.findByCode(airport.getAirportCode()).isPresent()) {
            throw new EntityExistExeption("Airport is exist! Code: " + airport.getAirportCode()) ;
        }
        airportDAO.insert(airport);
        LOG.log(Level.INFO, "Airport added");
        return true;
    }

    @Override
    public Airport getByCode(String code) {
        return airportDAO.findByCode(code).orElse(null);
    }

    @Override
    public List<Airport> getAll() {
        return airportDAO.findAll();
    }

    @Override
    public boolean updateByCode(Airport entity) {

        boolean isUpdate = airportDAO.updateByCode( entity);
        if (!isUpdate) {
            throw new IllegalArgumentException("Failed to update: Airport code does not exist or update was unsuccessful.");
        }
        return isUpdate;
    }

    @Override
    public boolean deleteByCode(String code) {
        
        boolean isDeleted = airportDAO.deleteByCode(code);
        if (!isDeleted) {
            throw new IllegalArgumentException("Failed to delete: Airport code does not exist or delete was unsuccessful.");
        }
        return isDeleted;
    }
    
    @Override
    public List<String> getKeyAirportForSearch(){
        return getAll().stream()
                .map(airport -> airport.getAirportCode() + " - " + airport.getAirportName() + " - " + airport.getCity())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<String> getAirportSuggesstions(String keyword){
        return airportDAO.findAirportSuggestions(keyword);
    }
    
    public static void main(String[] args) {
        AirportServiceImpl s = new AirportServiceImpl();
        
        List<String> lists = s.getKeyAirportForSearch();
        
        for (String list : lists) {
            System.out.println(list);
        }
    }
}
