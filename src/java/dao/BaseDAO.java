package dao;

import java.util.List;

public interface BaseDAO<T> {
    /**
     * find T by id
     * @param id
     * @return T object if id exist
     * else return null
     */
    T findByID(Long id);

    /**
     * get all urser from db
     * @return 
     */
    List<T> findAll();
    /**
     * insert new entity to database
     * @param entity
     * @return true if insert successfull
     * else return false
     */
    boolean insert (T entity);

    /**
     * update a entity
     * @param entity 
     */
    void update(T entity);

    /**
     * delete a entity T by id
     * @param id 
     */
    void deleteByID(long id);
    
    /**
     * get list of entity by a condition
     * @return a list of entity
     */
    List<T> selectByCondition();
}
