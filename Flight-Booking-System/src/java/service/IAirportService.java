/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.AirportEntity;

/**
 *
 * @author manhphong
 */
public interface IAirportService {

    /**
     * save an airport object
     *
     * @param airport
     * @return true if successful
     */
    boolean save(AirportEntity airport);

    /**
     * get airport object by airport code
     * @param code
     * @return an airport object
     */
    AirportEntity getByCode(String code);

    /**
     * get all airport form database
     * @return a list of airport
     */
    List<AirportEntity> getAll();

    /**
     * update an airport object by code
     * @param entity
     * @return true if update successfull
     */
    boolean updateByCode(AirportEntity entity);

    /**
     * delete an airport entity by code
     * @param code
     * @return true if delete successfull
     */
    boolean deleteByCode(String code);
    
}
