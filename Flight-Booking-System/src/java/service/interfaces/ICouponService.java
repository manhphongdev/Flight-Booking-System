package service.interfaces;

import java.util.List;
import model.Coupon;

/**
 *
 * @author manhphong
 */
public interface ICouponService {

    /**
     * save a coupon object
     *
     * @param coupon
     * @return true if successful
     */
    boolean save(Coupon coupon);

    /**
     * get coupon object by coupon code
     * @param code
     * @return a coupon object
     */
    Coupon getByCode(String code);

    /**
     * get all coupon from database
     * @return a list of coupon
     */
    List<Coupon> getAll();

    /**
     * update a coupon object by code
     * @param entity
     * @return true if update successfull
     */
    boolean updateByCode(Coupon entity);

    /**
     * delete a coupon entity by code
     * @param code
     * @return true if delete successfull
     */
    boolean deleteByCode(String code);
    
    /**
     * get coupon object by coupon ID
     * @param id
     * @return a coupon object
     */
    Coupon getByID(Long id);

    /**
     * update coupon status by ID
     * @param couponId
     * @param status
     * @return true if update successful
     */
    boolean updateStatus(Long couponId, String status);

    public List<Coupon> getByCondition(String search, String statusFilter, String sortBy);
}
