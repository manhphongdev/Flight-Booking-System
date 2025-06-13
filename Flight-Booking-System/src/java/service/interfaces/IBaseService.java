/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service.interfaces;

import java.util.List;

/**
 *
 * @author manhphong
 */
public interface IBaseService<T> {
    boolean save(T entity);
    T getByID(Long id);
    T getByName(String name);
    List<T> getALl();
    boolean updateById();
}
