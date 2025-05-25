
package dao;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author manhphong
 */
public interface IBaseDAO<T> {

    /**
     * find T by id
     *
     * @param id
     * @return T object if id exist else return null
     */
    Optional<T> findByID(Long id);

    /**
     * get all user from db
     *
     * @return
     */
    List<T> findAll();

    /**
     * insert new entity to database
     *
     * @param entity
     * @return id of entity
     */
    Long insert(T entity);

    /**
     * update a entity
     *
     * @param entity
     */
    boolean updateByID(Long id);

    /**
     * delete a entity T by id
     *
     * @param id
     */
    boolean deleteByID(Long id);

    /**
     * get list of entity by a condition
     *
     * @return a list of entity
     */
    List<T> selectByCondition();
}
