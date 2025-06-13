package dao.interfaces;

import java.util.List;
import model.Booking;

public interface IBookingDAO extends IBaseDAO<Booking> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByStatus(String status);
    List<Booking> findByCouponId(Long couponId);
    boolean updateStatus(Long bookingId, String status);
} 