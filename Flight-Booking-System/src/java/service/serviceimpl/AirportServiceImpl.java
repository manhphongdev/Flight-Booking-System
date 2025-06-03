/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.serviceimpl;

import dao.IAirportDAO;
import dao.daoimpl.AirportDAOImpl;
import exception.EntityExistExeption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AirportEntity;
import service.IAirportService;

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
    public boolean save(AirportEntity airport) {
        // check airport is exist
        if (airportDAO.findByCode(airport.getAirportCode()).isPresent()) {
            throw new EntityExistExeption("Airport is exist! Code: " + airport.getAirportCode()) ;
        }
        airportDAO.insert(airport);
        LOG.log(Level.INFO, "Airport added");
        return true;
    }

    @Override
    public AirportEntity getByCode(String code) {
        return airportDAO.findByCode(code).orElse(null);
    }

    @Override
    public List<AirportEntity> getAll() {
        return airportDAO.findAll();
    }

    @Override
    public boolean updateByCode(AirportEntity entity) {

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

}
