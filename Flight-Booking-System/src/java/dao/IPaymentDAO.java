package dao;

import java.time.LocalDateTime;
import java.util.List;
import model.Payment;

public interface IPaymentDAO extends IBaseDAO<Payment> {
    List<Payment> findByBookingId(Long bookingId);
    List<Payment> findByStatus(String status);
    List<Payment> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    boolean updateStatus(Long paymentId, String status);
} 