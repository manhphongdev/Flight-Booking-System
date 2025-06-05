package dao.daoimpl;

import dao.IBookingDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import utils.DBContext;

/**
 * 
 * @author Administrator
 * @version 1.0
 */
public class BookingDAOImpl implements IBookingDAO {
    
    private static final Logger logger = Logger.getLogger(BookingDAOImpl.class.getName());

    @Override
    public Optional<Booking> findByID(Long id) {
        String sql = "SELECT * FROM [Booking] WHERE booking_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(bookingMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding booking by ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM [Booking]";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookings.add(bookingMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding all bookings: {0}", e.getMessage());
        }
        return bookings;
    }

    @Override
    public Long insert(Booking booking) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO [Booking] (user_id, coupon_id, total_price, status, created_at, updated_at) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?)");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setLong(1, booking.getUserId());
            ps.setLong(2, booking.getCouponId());
            ps.setBigDecimal(3, booking.getTotalPrice());
            ps.setString(4, booking.getStatus());
            ps.setTimestamp(5, new Timestamp(booking.getCreatedAt().getTime()));
            ps.setTimestamp(6, new Timestamp(booking.getUpdatedAt().getTime()));
            
            if (ps.executeUpdate() == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        booking.setBookingId(rs.getLong(1));
                        return booking.getBookingId();
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting booking: {0}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateByID(Long id, Booking booking) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE [Booking] SET user_id = ?, coupon_id = ?, total_price = ?, ");
        sql.append("status = ?, updated_at = ? WHERE booking_id = ?");
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setLong(1, booking.getUserId());
            ps.setLong(2, booking.getCouponId());
            ps.setBigDecimal(3, booking.getTotalPrice());
            ps.setString(4, booking.getStatus());
            ps.setTimestamp(5, new Timestamp(booking.getUpdatedAt().getTime()));
            ps.setLong(6, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating booking: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByID(Long id) {
        String sql = "DELETE FROM [Booking] WHERE booking_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting booking: {0}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Booking> selectByCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM [Booking] WHERE user_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookings.add(bookingMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding bookings by user ID: {0}", e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> findByStatus(String status) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM [Booking] WHERE status = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookings.add(bookingMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding bookings by status: {0}", e.getMessage());
        }
        return bookings;
    }

    @Override
    public List<Booking> findByCouponId(Long couponId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM [Booking] WHERE coupon_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, couponId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookings.add(bookingMapper(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error finding bookings by coupon ID: {0}", e.getMessage());
        }
        return bookings;
    }

    @Override
    public boolean updateStatus(Long bookingId, String status) {
        String sql = "UPDATE [Booking] SET status = ?, updated_at = GETDATE() WHERE booking_id = ?";
        
        try (Connection conn = DBContext.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setLong(2, bookingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating booking status: {0}", e.getMessage());
            return false;
        }
    }

    private Booking bookingMapper(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getLong("booking_id"));
        booking.setUserId(rs.getLong("user_id"));
        booking.setCouponId(rs.getLong("coupon_id"));
        booking.setTotalPrice(rs.getBigDecimal("total_price"));
        booking.setStatus(rs.getString("status"));
        booking.setCreatedAt(rs.getTimestamp("created_at"));
        booking.setUpdatedAt(rs.getTimestamp("updated_at"));
        return booking;
    }
} 