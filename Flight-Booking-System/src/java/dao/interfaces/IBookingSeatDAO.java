package dao.interfaces;

import java.util.List;
import model.BookingSeat;

public interface IBookingSeatDAO extends IBaseDAO<BookingSeat> {
    List<BookingSeat> findByBookingId(Long bookingId);
    List<BookingSeat> findBySeatId(Long seatId);
    List<BookingSeat> findByPassengerId(Long passengerId);
    boolean deleteByBookingId(Long bookingId);
} 