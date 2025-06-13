/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao.interfaces;

import java.util.List;
import model.Fee;

/**
 *
 * @author manhphong
 */
public interface IFeeDAO {
    Fee getFeeByName(String feeName);
    List<Fee> getAllActiveFees();
    boolean updateFee(Fee fee);
}
