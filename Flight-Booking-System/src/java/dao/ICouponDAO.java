package dao;

import java.time.LocalDateTime;
import java.util.List;
import model.Coupon;

public interface ICouponDAO extends IBaseDAO<Coupon> {
    List<Coupon> findByCode(String code);
    List<Coupon> findByStatus(String status);
    List<Coupon> findByExpiryDate(LocalDateTime expiryDate);
    boolean updateStatus(Long couponId, String status);
} 