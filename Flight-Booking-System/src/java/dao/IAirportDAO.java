/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import java.util.List;
import java.util.Optional;
import model.AirportEntity;


/**
 *
 * @author manhphong
 */
public interface IAirportDAO {

       boolean insert(AirportEntity airport);
       List<AirportEntity> findAll();
       Optional<AirportEntity> findByCode(String code);
       boolean updateByCode(AirportEntity entity);
       boolean deleteByCode(String code);
       
}
