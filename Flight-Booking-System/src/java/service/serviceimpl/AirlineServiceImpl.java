package service.serviceimpl;

import dao.IAirlineDAO;
import dao.daoimpl.AirlineDAOImpl;
import exception.EntityExistExeption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Airline;
import service.interfaces.IAirlineService;

public class AirlineServiceImpl implements IAirlineService {

    private final IAirlineDAO airlineDAO;
    private static final Logger LOG = Logger.getLogger(AirlineServiceImpl.class.getName());

    public AirlineServiceImpl() {
        this.airlineDAO = new AirlineDAOImpl();
    }

    @Override
    public boolean save(Airline airline) {
        // check airline is exist
        if (airlineDAO.findByCode(airline.getAirlineCode()).isPresent()) {
            throw new EntityExistExeption("Airline is exist! Code: " + airline.getAirlineCode());
        }
        airlineDAO.insert(airline);
        LOG.log(Level.INFO, "Airline added");
        return true;
    }

    @Override
    public Airline getByCode(String code) {
        return airlineDAO.findByCode(code).orElse(null);
    }

    @Override
    public List<Airline> getAll() {
        return airlineDAO.findAll();
    }



    @Override
    public boolean deleteByCode(String code) {
        boolean isDeleted = airlineDAO.deleteByCode(code);
        if (!isDeleted) {
            throw new IllegalArgumentException("Failed to delete: Airline code does not exist or delete was unsuccessful.");
        }
        return isDeleted;
    }
    
    public static void main(String[] args) {
        AirlineServiceImpl a = new AirlineServiceImpl();
        
        List<Airline> list = new ArrayList<>();
        for (Airline airline : list) {
            System.out.println(airline.toString());
        }
    }
} 