package dao.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import model.Coupon;

public interface ICouponDAO extends IBaseDAO<Coupon> {
    List<Coupon> findByCode(String code);
    List<Coupon> findByStatus(String status);
    List<Coupon> findByEndDate(LocalDateTime endDate);
    boolean updateStatus(Long couponId, String status);

    public List<Coupon> selectByCondition(String search, String status, String sortBy);
} 