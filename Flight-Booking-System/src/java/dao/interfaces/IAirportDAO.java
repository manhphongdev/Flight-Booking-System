/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao.interfaces;

import java.util.List;
import java.util.Optional;
import model.Airport;


/**
 *
 * @author manhphong
 */
public interface IAirportDAO {

       boolean insert(Airport airport);
       List<Airport> findAll();
       Optional<Airport> findByCode(String code);
       boolean updateByCode(Airport entity);
       boolean deleteByCode(String code);
       public List<String> findAirportSuggestions(String keyword);
}
